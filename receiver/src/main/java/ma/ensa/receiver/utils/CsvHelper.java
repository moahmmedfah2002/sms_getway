package ma.ensa.receiver.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import ma.ensa.receiver.dto.CsvReceiverDto;
import ma.ensa.receiver.dto.ReceiverDto;
import ma.ensa.receiver.execptions.InvalidCsvFormatException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class CsvHelper {

    private static final String TYPE = "text/csv";
    private static final String[] HEADERS = {"name", "phoneNumber"};

    /**
     * Check if the file is a CSV file
     */
    public boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    /**
     * Parse CSV file to list of ReceiverDto objects
     */
    public List<CsvReceiverDto> parseCsvFile(MultipartFile file) throws InvalidCsvFormatException {
        if (!hasCSVFormat(file)) {
            throw new InvalidCsvFormatException("Not a Csv File");
        }

        List<CsvReceiverDto> receivers;

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<CsvReceiverDto> csvToBean = new CsvToBeanBuilder<CsvReceiverDto>(reader)
                    .withType(CsvReceiverDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(0) // Header is read by annotations
                    .build();

            receivers = csvToBean.parse();

            // Sanitize
            receivers.forEach(CsvReceiverDto::sanitize);

        } catch (Exception e) {
            throw new InvalidCsvFormatException("Failed to parse CSV file: " + e.getMessage());
        }

        return receivers;
    }
}

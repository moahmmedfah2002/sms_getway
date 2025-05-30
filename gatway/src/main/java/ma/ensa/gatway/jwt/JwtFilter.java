package ma.ensa.gatway.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String path = request.getServletPath();
            if (path.startsWith("/auth/signup") || path.startsWith("/auth/login")) {
                filterChain.doFilter(request, response);
                return;
            }


            String header = request.getHeader("Authorization");
            if (header == null || header.isEmpty()) {

                System.out.println("register or login should pass here");
                filterChain.doFilter(request, response);
            }
            if (header == null || !header.startsWith("Bearer ")) {
                System.out.println("register or login should pass here too");
                filterChain.doFilter(request, response);
            }
            System.out.println("achieved line 45 in filter of gateway");
            assert header != null;
            System.out.println("header: " + header);
            String jwt = header.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);
            System.out.println("userEmail: " + userEmail);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                System.out.println("userDetails: " + userDetails);

                if (jwtService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    System.out.println("authenticated");

                }
                System.out.println("pass to filter");
                filterChain.doFilter(request,response);



            }
        }catch (Exception e){
            System.out.println("Exception in jwt filter");
            System.out.println(e.getMessage());
            filterChain.doFilter(request, response);
        }



    }
}

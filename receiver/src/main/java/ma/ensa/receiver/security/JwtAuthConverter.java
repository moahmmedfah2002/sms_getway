package ma.ensa.receiver.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;


/**
 * JwtAuthConverter converts a Jwt into an {@link org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken}
 * with extracted authorities.
 *
 * <p>
 * It extracts default roles using {@link org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter}
 * and can be extended to extract additional roles from the {@code realm_access} claim.
 * Example JWT claim:
 * <pre>
 * "realm_access": {
 *   "roles": [
 *     "default-roles-myrealm",
 *     "offline_access",
 *     "ADMIN",
 *     "uma_authorization",
 *     "USER"
 *   ]
 * }
 * </pre>
 * </p>
 *
 * @see org.springframework.security.oauth2.jwt.Jwt
 * @see org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
 */

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                extractResourceRoles(source)

        ).toList();
        return new JwtAuthenticationToken(source, authorities, source.getClaimAsString("preferred_username"));
    }

    private Stream<? extends GrantedAuthority> extractResourceRoles(Jwt source) {
        Map<String, Object> realmAccess = source.getClaim("realm_access");
        if (realmAccess == null || realmAccess.isEmpty()) {
            return Stream.empty();
        }
        Object rolesObj = realmAccess.get("roles");
        if (!(rolesObj instanceof Collection<?> roles)) {
            return Stream.empty();
        }
        return roles.stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
//                .map(role -> "ROLE_" + role) // if you want them to be roles not authorities and also to use hasRole instead of hasAuthority
                .map(SimpleGrantedAuthority::new);
    }
}

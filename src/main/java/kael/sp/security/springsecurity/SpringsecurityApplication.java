package kael.sp.security.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

}

@RestController
class HttpController {

	@GetMapping("/public")
	String publicRoute() {
		return "<h1>Public route, feel to good</h1>";
	}

	@GetMapping("/cookie")
	String cookie(@AuthenticationPrincipal OidcUser principal) {
		return String.format(
				"""
						<h1>Testing spring security with OAUTH2</h1>
						<h3>principa: %s</h3>
						<h4>e-mail: %s</h4>
						<h4>authorities: %s</h4>
						<h4>JWT: %s</h4>

							""", principal, principal.getAttribute("email"),
				principal.getAuthorities(),
				principal.getIdToken().getTokenValue());
	}

	String privateRute() {
		return "<h1>SPRING SECURITY, PRIVATE ROUTE!</h1>";
	}

	@GetMapping("/jwt")
	String jwt(@AuthenticationPrincipal Jwt jwt){
		return String.format(
				"""
					Principal: %s\n
					E-mail: %s\n
					JWT: %s\n
				""",
	jwt.getClaims(), jwt.getClaim("email"), jwt.getTokenValue());
	}
}
package org.group2.security.JWT;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.group2.Model.UserLogin;
import org.group2.Service.IUserLoginService;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/user")
public class AuthenticationREST {
    @Inject
    private IUserLoginService userLoginService;

    @ConfigProperty(name = "com.ard333.quarkusjwt.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

	@PermitAll
	@POST @Path("/login") @Produces(MediaType.APPLICATION_JSON)
	public Response login(Credentials credentials) {
		UserLogin u = userLoginService.findByUsername(credentials.getUsername());
		if (u != null && BcryptUtil.matches(credentials.getPassword(), u.getPassword())) {
			try {
				return Response.ok(new AuthResponse(TokenUtils.generateToken(u.getUsername(), u.getRol(), duration, issuer))).build();
			} catch (Exception e) {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}

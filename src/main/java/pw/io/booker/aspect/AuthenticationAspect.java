package pw.io.booker.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import pw.io.booker.repo.AuthenticationRepository;

@Aspect
@Component
public class AuthenticationAspect {

	private AuthenticationRepository authenticationRepository;

	public AuthenticationAspect(AuthenticationRepository authenticationRepository) {
		super();
		this.authenticationRepository = authenticationRepository;
	}

	@Around("execution(* pw.io.booker.controller..*(..)) && args(token,..) && "
			+ "!execution(* pw.io.booker.controller.CustomerController.saveAll(..)), )*")
	public Object checkAuthentication(ProceedingJoinPoint joinPoint, String token) throws Throwable{
		if(token == null) {
			throw new RuntimeException("Access Denied");
		}
		
		if(!authenticationRepository.findByToken(token).isPresent()) {
			throw new RuntimeException("Access Denied");
		}
		return joinPoint.proceed();
	}
	
	@Before("execution(* pw.io.booker.controller..*(..)) && args(token,..)")
	public void beforeAuthentication(JoinPoint joinPoint, String token) throws Throwable{
		Logger logger = Logger.getLogger(AuthenticationAspect.class);
		logger.info("Before: " + joinPoint.getSignature().getName());
	}
	
	@After("execution(* pw.io.booker.controller..*(..)) && args(token,..)")
	public void afterAuthentication(JoinPoint joinPoint, String token) throws Throwable{
		Logger logger = Logger.getLogger(AuthenticationAspect.class);
		logger.info("After: " + joinPoint.getSignature().getName());
	}
	
}

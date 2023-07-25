package tp.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RegisseurAspect {
	
	@Pointcut("execution(public void tp.spring.*.jouer())")
	public void pointcut() {}
	
	@Before("pointcut()")
	public void eteindreLumiere() {
		System.out.println("Le régisseur éteint les lumières");
	}
	
	@Around("pointcut()")
	public void gestionRideaux(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Ouverture des rideaux");
		
		pjp.proceed();
		
		System.out.println("Fermeture des rideaux");
	}
	
	@Before("@annotation(tp.spring.aspect.Silence)")
	public void demanderSilence() {
		System.out.println("CHUUTTTTT");
	}
}

package tp.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RegisseurAspect {
	@Before("execution(public void tp.spring.*.jouer())")
	public void eteindreLumiere() {
		System.out.println("Le régisseur éteint les lumières");
	}
}

package nephilim.study.spring.ch05;

import static org.junit.Assert.*;

import org.junit.*;

public class EnumLearningTest {
	public enum Planet {
		MERCURY(3.302e+23, 2.439e6),
		VENUS(4.869e+24, 6.052e6),
		EARTH(5.975e+24, 6.378e6);
		
		private final double mass;
		private final double radius;
		
		private static final double G = 6.67300E-11;
		
		Planet(double mass, double radius) {
			this.mass = mass;
			this.radius = radius;
		}
		
		public double surface() {
			return 4.0 / 3.0 * Math.PI * (radius * radius * radius); 
		}
	}
	
	@Test
	public void learnEnum() {
		
		Planet[] planets = Planet.values();
		
		assertTrue(planets.length == 3);
		
		for ( Planet planet:planets) {
			System.out.printf("%d] %s: surface = %f%n", planet.ordinal(), planet, planet.surface());
		}
	}
}

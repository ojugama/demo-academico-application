package co.edu.demoacademico.config;

import co.edu.demoacademico.model.EstudianteEntity;
import co.edu.demoacademico.repository.EstudianteRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataSeeder {
    @Value("${app.seed.enabled:true}")
    private boolean enabled;

    @Value("${app.seed.cantidad:100}")
    private int cantidad;

    @Bean
    CommandLineRunner seedEstudiantes(EstudianteRepository estudianteRepository) {
        return args -> {
            if (!enabled) return;

            Faker faker = new Faker(new Locale("es"));
            int count = 0;

            while (count < cantidad) {
                String nombre = faker.name().fullName();
                String email = ("est" + count + "_" + faker.internet().emailAddress())
                        .toLowerCase()
                        .replace(" ", "")
                        .replace("..", ".");

                EstudianteEntity estudiante = new EstudianteEntity();
                estudiante.setNombre(nombre);
                estudiante.setEmail(email);

                try {
                    estudianteRepository.save(estudiante);
                    count++;
                } catch (Exception ex) {
                    // Si el email ya existe, se genera un nuevo email y se intenta guardar nuevamente
                }
            }
        };
    }
}

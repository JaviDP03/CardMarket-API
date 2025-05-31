package com.daw.cardmarket.service;

import com.daw.cardmarket.model.Admin;
import com.daw.cardmarket.model.Roles;
import com.daw.cardmarket.repository.AdminRepository;
import com.daw.cardmarket.utils.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
    public boolean createAdmin(Admin admin) {
        admin.setRol(Roles.ADMIN);
        admin.setContrasenna(passwordEncoder.encode(admin.getContrasenna()));

        Admin adminC = adminRepository.save(admin);

        return adminC.getId() != null;
    }

    @Transactional
    public boolean updateAdmin(Admin adminU) {
        Admin admin = jwtUtils.userLogin();

        if (admin != null) {
            admin.setNombre(adminU.getNombre());
            admin.setApellido(adminU.getApellido());
            admin.setEmail(adminU.getEmail());
            admin.setImagenB64(adminU.getImagenB64());

            if (adminU.getContrasenna() != null && !adminU.getContrasenna().isEmpty()) {
                admin.setContrasenna(passwordEncoder.encode(adminU.getContrasenna()));
            }

            adminRepository.save(admin);

            return true;
        }

        return false;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(int id) {
        Admin admin = adminRepository.findById(id).orElse(null);

        if (admin != null) {
            admin.setContrasenna(null);
        }

        return Optional.ofNullable(admin);
    }

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByNombreUsuario(username);
    }

    @Transactional
    public boolean deleteAdmin(int id) {
        Admin admin = jwtUtils.userLogin();

        if (admin != null) {
            adminRepository.deleteById(id);

            return true;
        }

        return false;
    }

    public void createDefaultAdmin() {
        if (getAllAdmins().isEmpty()) {
            System.out.println("No existe ningún Administrador!!");
            System.out.println("Creando Administrador por defecto...");
            Admin admin = new Admin();
            admin.setNombreUsuario("admin");
            admin.setContrasenna(passwordEncoder.encode("admin"));
            admin.setNombre("Admin");
            admin.setEmail("admin@cardmarket.com");
            admin.setRol(Roles.ADMIN);
            adminRepository.save(admin);

            System.out.println("Creado Administrador (admin)");
        } else {
            System.out.println("Ya existe un Administrador");
        }
    }

    public File executePostgresqlBackup() {
        try {
            // Determinar el comando según el sistema operativo
            String command;
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                command = ".\\mvnw.cmd";
            } else {
                command = "./mvnw";
            }

            ProcessBuilder processBuilder = new ProcessBuilder(
                command, 
                "exec:exec@backup-postgresql-sql"
            );

            // Establecer el directorio de trabajo (raíz del proyecto)
            File projectDir = new File(System.getProperty("user.dir"));
            processBuilder.directory(projectDir);

            // Redirigir errores al output estándar
            processBuilder.redirectErrorStream(true);

            System.out.println("Iniciando backup de PostgreSQL...");
            Process process = processBuilder.start();

            // Leer la salida del comando para mostrar progreso
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup de PostgreSQL completado exitosamente");

                // Buscar el archivo generado en la ruta definida en el pom.xml (cardmarket_backup.sql)
                File backupFile = new File(projectDir, "cardmarket_backup.sql");

                if (backupFile.exists()) {
                    System.out.println("Archivo de backup encontrado: " + backupFile.getAbsolutePath());
                    return backupFile;
                } else {
                    System.err.println("No se encontró el archivo de backup en la ubicación esperada");
                    return null;
                }
            } else {
                System.err.println("Error al ejecutar backup. Código de salida: " + exitCode);
                return null;
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar el comando de backup: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public File generateDependencyCheckHtmlReport() {
        try {
            String command;
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                command = ".\\mvnw.cmd";
            } else {
                command = "./mvnw";
            }

            ProcessBuilder processBuilder = new ProcessBuilder(
                command,
                "dependency-check:check"
            );

            File projectDir = new File(System.getProperty("user.dir"));
            processBuilder.directory(projectDir);
            processBuilder.redirectErrorStream(true);

            System.out.println("Ejecutando dependency-check:check...");
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                // El reporte HTML suele generarse en target/dependency-check-report.html
                File htmlReport = new File(projectDir, "target/dependency-check/dependency-check-report.html");
                if (htmlReport.exists()) {
                    System.out.println("Reporte HTML generado: " + htmlReport.getAbsolutePath());
                    return htmlReport;
                } else {
                    System.err.println("No se encontró el reporte HTML en la ubicación esperada");
                    return null;
                }
            } else {
                System.err.println("Error al ejecutar dependency-check. Código de salida: " + exitCode);
                return null;
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al ejecutar dependency-check: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}

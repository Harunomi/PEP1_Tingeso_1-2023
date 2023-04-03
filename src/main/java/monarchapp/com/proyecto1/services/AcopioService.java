package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.AcopioEntity;
import monarchapp.com.proyecto1.repositories.AcopioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@Service
public class AcopioService {
    private String carpeta = "Archivos//";

    @Autowired
    private AcopioRepository acopioRepository;
    private AcopioEntity acopioEntity;
    private File archivo;

    public String save(MultipartFile file){
        if(!file.isEmpty()){
            try{
                byte [] bytes= file.getBytes();
                Path path = Paths.get(carpeta+file.getOriginalFilename());
                Files.write(path,bytes);
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        archivo = new File(carpeta + file.getOriginalFilename());
        try{
            Scanner obj = new Scanner(archivo);
            while(obj.hasNextLine()){
                acopioEntity = new AcopioEntity();
                String[] partes = obj.nextLine().split(";");
                acopioEntity.setFecha(partes[0]);
                acopioEntity.setTurno(partes[1]);
                acopioEntity.setProveedor(partes[2]);
                acopioEntity.setKLS(Integer.valueOf(partes[3]));
                acopioRepository.save(acopioEntity);

            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        return "Archivo guardado exitosamente";
    }

    public void borrarTodo(){
        acopioRepository.deleteAll();
    }
}

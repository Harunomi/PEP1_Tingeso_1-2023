package monarchapp.com.proyecto1.services;

import monarchapp.com.proyecto1.entities.GrasaSolidoEntity;
import monarchapp.com.proyecto1.repositories.GrasaSolidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class GrasaSolidoService {
    private String carpeta = "Archivos//";

    @Autowired
    private GrasaSolidoRepository  grasaSolidoRepository;
    private GrasaSolidoEntity grasaSolidoEntity;
    private File archivo;

    public String save(MultipartFile file){
        if(!file.isEmpty()){
            try{
                byte [] bytes = file.getBytes();
                Path path = Paths.get(carpeta+file.getOriginalFilename());
                Files.write(path,bytes);
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        archivo = new File(carpeta + file.getOriginalFilename());
        try {
            Scanner obj = new Scanner(archivo);
            while(obj.hasNextLine()){
                grasaSolidoEntity = new GrasaSolidoEntity();
                String[] partes = obj.nextLine().split(";");
                grasaSolidoEntity.setProveedor(partes[0]);
                grasaSolidoEntity.setGrasa(Float.parseFloat(partes[1]));
                grasaSolidoEntity.setSolido(Float.parseFloat(partes[2]));
                grasaSolidoRepository.save(grasaSolidoEntity);

            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        return "Archivo guardado exitosamente";
    }

    public void borrarTodo(){
        grasaSolidoRepository.deleteAll();
    }

    public ArrayList<GrasaSolidoEntity> obtenergrasaSolidos(){
        return (ArrayList<GrasaSolidoEntity>) grasaSolidoRepository.findAll();
    }
}

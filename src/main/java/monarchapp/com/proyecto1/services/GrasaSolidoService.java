package monarchapp.com.proyecto1.services;

import lombok.Generated;
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
    @Autowired
    private GrasaSolidoRepository  grasaSolidoRepository;
    private GrasaSolidoEntity grasaSolidoEntity;
    private File archivo;

    @Generated
    public String save(MultipartFile file){
        // se verifica que el archivo no sea nulo
        if(!file.isEmpty()){
            try{
                // se obtiene el contenido y la ubicacion
                byte [] bytes = file.getBytes();
                Path path = Paths.get(file.getOriginalFilename());
                Files.write(path,bytes);
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        archivo = new File(file.getOriginalFilename());
        try {
            Scanner obj = new Scanner(archivo);
            while(obj.hasNextLine()){ // buscamos linea a linea
                grasaSolidoEntity = new GrasaSolidoEntity(); // se crea un objeto de tipo grasaSolido
                String[] partes = obj.nextLine().split(";"); // se separa por ;
                grasaSolidoEntity.setProveedor(partes[0]);
                grasaSolidoEntity.setGrasa(Float.parseFloat(partes[1]));
                grasaSolidoEntity.setSolido(Float.parseFloat(partes[2]));
                grasaSolidoRepository.save(grasaSolidoEntity); //se guarda el objeto creado en la base de datos

            }
            obj.close();
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

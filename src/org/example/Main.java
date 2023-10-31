package org.example;

import org.example.domain.Entity;
import org.example.domain.Prietenie;
import org.example.domain.Tuple;
import org.example.domain.Utilizator;
import org.example.domain.validators.UtilizatorValidator;
import org.example.repository.InMemoryRepository;
import org.example.repository.Repository;
import org.example.service.EntityService;
import org.example.service.PrietenieService;
import org.example.service.Service;
import org.example.service.UtilizatorService;
import org.example.domain.validators.PrietenieValidator;

import org.example.domain.UserInterface;


import javax.xml.stream.events.EntityReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.SocketHandler;

public class Main {

    public static void main(String[] args) {

        Utilizator u1=new Utilizator("u1FirstName", "u1LastName");
        u1.setId(1l);
        Utilizator u2=new Utilizator("u2FirstName", "u2LastName"); u2.setId(2l);
        Utilizator u3=new Utilizator("u3FirstName", "u3LastName"); u3.setId(3l);
        Utilizator u4=new Utilizator("u4FirstName", "u4LastName"); u4.setId(4l);
        Utilizator u5=new Utilizator("u5FirstName", "u5LastName"); u5.setId(5l);
        Utilizator u6=new Utilizator("u6FirstName", "u6LastName"); u6.setId(6l);
        Utilizator u7=new Utilizator("u7FirstName", "u7LastName"); u7.setId(7l);

        InMemoryRepository<Long, Utilizator> repo=new InMemoryRepository<>(new UtilizatorValidator());
        repo.save(u1);
        repo.save(u2);
        repo.save(u3);
        repo.save(u4);
        repo.save(u5);
        repo.save(u6);
        repo.save(u7);

        System.out.println("ok");
        EntityService<Long,Utilizator>entityService=new EntityService<>(repo);
        entityService.deleteEntity(1l);
        Iterable<Utilizator>users= entityService.findAllEntities();

        for (Utilizator user : users)
        {
            System.out.println(user.toString());
        }
        UtilizatorService utilizatorService=new UtilizatorService(repo);
        /*System.out.println("--------------Meniu-------------------");
        System.out.println("1.Add utilizator.\n");
        System.out.println("2.Delete Utilizator.\n");
        System.out.println("3.Add prieten.\n");
        System.out.println("4.Delete prieten.\n");
        System.out.println("5.Afisare numar comunitati.\n");
        System.out.println("6.Afisare cea mai sociabila comunitate.\n");*/

        utilizatorService.addUser(u1);
        Iterable<Utilizator>utilizs=utilizatorService.findAllEntities();
        for(Utilizator user:utilizs)
        {
            System.out.println(user.toString());
        }
        Repository<Tuple<Long,Long>, Prietenie>prietenieRepository=new InMemoryRepository<Tuple<Long, Long>, Prietenie>(new PrietenieValidator());
//        Repository<Long,Utilizator> utilizatorRepository=new InMemoryRepository<>(new UtilizatorValidator());

        PrietenieService prietenieService=new PrietenieService(prietenieRepository, repo);

        Tuple<Long,Long>prietenieID=new Tuple<>(u1.getId(),u2.getId());
        Prietenie prietenie=new Prietenie();
        prietenie.setId(prietenieID);
        //prietenie.getId();
        prietenieService.addUser(prietenie);
        System.out.println(prietenie);

        Iterable<Prietenie> prietenii=prietenieService.findAllEntities();
        for (Prietenie prietenie1:prietenii)
        {
            System.out.println("Prietenie: "+prietenie1);
        }
        prietenieService.deleteEntity(prietenie.getId());
        Iterable<Prietenie> prieten=prietenieService.findAllEntities();
        for (Prietenie prietenie1:prieten)
        {
            System.out.println("Prietenie: "+prietenie1);
        }
        System.out.println("--");

        UserInterface userInterface=new UserInterface(utilizatorService,prietenieService);
        userInterface.run();

        Iterable<Utilizator> brr= utilizatorService.findAllEntities();
        for (Utilizator user : brr)
        {
            System.out.println(user.toString());
        }



    }
}
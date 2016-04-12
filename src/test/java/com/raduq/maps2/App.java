package com.raduq.maps2;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Raduq on 04/04/2016.
 */
public class App {

    private static List<Person> people;

    @BeforeClass
    public static void before() {
        people = new ArrayList<>();
        people.add(new Person("Abelardo"));
        people.add(new Person("Maria"));
        people.add(new Person("Pedro"));
        people.add(new Person("Nucky"));
    }

    @Test
    public void code1() throws Exception {
        List<Person> loggedIn = new ArrayList<>();
        for(Person person : people){
            loggedIn.add(person.addActivity(new Activity("login")));
        }
        Assert.assertNotNull(loggedIn);
        Assert.assertFalse(loggedIn.isEmpty());
    }


    @Test
    public void code1Map() throws Exception {
        Stream<Person> streamPerson = people.stream();
        Stream<Person> streamWithAct = streamPerson.map(person -> person.addActivity(new Activity("login")));
        List<Person> loggedIn = streamWithAct.collect(Collectors.toList());
        Assert.assertNotNull(loggedIn);
        Assert.assertFalse(loggedIn.isEmpty());
    }

    @Test
    public void code1MapSimple() throws Exception {
        List<Person> loggedIn = people.stream().map(person -> person.addActivity(new Activity("login"))).collect(Collectors.toList());
        Assert.assertNotNull(loggedIn);
        Assert.assertFalse(loggedIn.isEmpty());
    }

    @Test
    public void code2() throws Exception {
        //primeiro adiciono atividades à todas as pessoas
        people = people.stream().map(person -> person.addActivity(new Activity("login"))).collect(Collectors.toList());

        int actNumber = 0;
        for(Person person : people){
            actNumber += person.getActivities().size();
        }
        System.out.println(actNumber);
    }

    @Test
    public void code2Map() throws Exception {
        //primeiro adiciono atividades à todas as pessoas
        people = people.stream().map(person -> person.addActivity(new Activity("login"))).collect(Collectors.toList());

        int actNumber = people.stream().mapToInt(person -> person.getActivities().size()).sum();
        System.out.println(actNumber);
    }

    @Test
    public void code3(){
        //primeiro adiciono atividades à todas as pessoas
        people = people.stream().map(person -> person.addActivity(new Activity("jogo"))).collect(Collectors.toList());
        people.addAll( people.stream().map(person -> person.addActivity(new Activity("balé"))).collect(Collectors.toList()));
        people.addAll(  people.stream().map(person -> person.addActivity(new Activity("corrida"))).collect(Collectors.toList()));

        Set<Activity> activities = new HashSet<>();
        for(Person person : people){
            for(Activity activity : person.getActivities()){
                if(!"jogo".equals(activity.getName())){
                    activities.add(activity);
                }
            }
        }
        System.out.println(activities.size() + " atividades encontradas (excluindo jogo).");
    }

    @Test
    public void code3FlatMap() {
        //primeiro adiciono atividades à todas as pessoas
        people = people.stream().map(person -> person.addActivity(new Activity("jogo"))).collect(Collectors.toList());
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("balé"))).collect(Collectors.toList()));
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("corrida"))).collect(Collectors.toList()));

        Set<Activity> activities = people.stream()
                .flatMap(person -> person.getActivities().stream())
                .filter(act -> !"jogo".equals(act.getName()))
                .collect(Collectors.toSet());
        System.out.println(activities.size() + " atividades encontradas (excluindo jogo).");
    }

    @Test
    public void code4Sort() throws Exception {
        //primeiro adiciono atividades à todas as pessoas
        people = people.stream().map(person -> person.addActivity(new Activity("jogo"))).collect(Collectors.toList());
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("balé"))).collect(Collectors.toList()));
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("corrida"))).collect(Collectors.toList()));
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("caminhar"))).collect(Collectors.toList()));
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("piscina"))).collect(Collectors.toList()));
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("praia"))).collect(Collectors.toList()));

        String acts = people.stream()
                .flatMap(person -> person.getActivities().stream())
                .filter(act -> !"jogo".equals(act.getName()))
                .distinct()
                .map(Activity::getName)
                .sorted()
                .collect(Collectors.joining(","));
        System.out.println("Atividades em ordem natural: " + acts);
    }

    @Test
    public void code4SortInverse() throws Exception {
        //primeiro adiciono atividades à todas as pessoas
        people = people.stream().map(person -> person.addActivity(new Activity("jogo"))).collect(Collectors.toList());
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("balé"))).collect(Collectors.toList()));
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("corrida"))).collect(Collectors.toList()));
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("caminhar"))).collect(Collectors.toList()));
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("piscina"))).collect(Collectors.toList()));
        people.addAll(people.stream().map(person -> person.addActivity(new Activity("praia"))).collect(Collectors.toList()));

        String acts = people.stream()
                .flatMap(person -> person.getActivities().stream())
                .filter(act -> !"jogo".equals(act.getName()))
                .distinct()
                .map(Activity::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(","));
        System.out.println("Atividades em ordem inversa: " + acts);
    }
}

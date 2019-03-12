import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task {
    private List<MonitoredData> data = new ArrayList<>();

    public Task() {
        try {
            Stream<String> stream = Files.lines(Paths.get("E://Proiecte/TP/tema5/Activities.txt"));
            stream.forEach(s->{
                System.out.println(s);
                String[] elements = s.split("\t\t");
                data.add(new MonitoredData(elements[0],elements[1],elements[2]));});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        for (MonitoredData i : data) {
            System.out.println(i.getStartTime() + "     " + i.getEndTime() + "      " + i.getActivity());
        }
    }

    public long task1() {

        return data.stream().map(s->{String[] elements = s.getStartTime().split(" ");
        return elements[0];}).distinct().count();
    }

    public void task2() {
        Map<String,Long> activities;
        activities = data.stream()
                .map(s->s.getActivity())
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        try (PrintWriter writer = new PrintWriter("count_activities.txt", "UTF-8")) {
            for (Map.Entry m : activities.entrySet())
                writer.println(m.getKey() + " " + m.getValue());
            writer.close();
            System.out.println("Task_2: OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void task3() {
        Map<String,Map<String,Long>> activities;
        activities = data.stream()
                .collect(Collectors.groupingBy(s->{String[] elements = s.getStartTime().split(" ");
                    return elements[0];},Collectors.groupingBy(MonitoredData::getActivity,Collectors.counting())));

        try (PrintWriter writer = new PrintWriter("day_activities.txt", "UTF-8")) {
            for (Map.Entry m : activities.entrySet()) {
                writer.println(m.getKey() + ":");
                for(Map.Entry entry :  ((Map<String, Long>) m.getValue()).entrySet())
                    writer.println(entry.getKey() + " " + entry.getValue());
                writer.println("");
            }
            writer.close();
            System.out.println("Task_3: OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void task4() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Long> activities;
        activities = data.stream()
                .collect(Collectors.groupingBy(s->s.getActivity(),
        Collectors.summingLong(s->{try {
            Date date1 = df.parse(s.getEndTime());
            Date date2 = df.parse(s.getStartTime());
            long diffInMillies = date1.getTime() - date2.getTime();
            return diffInMillies/3600000;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }}
        )));

        List<Map.Entry<String, Long>> activities_10;
        activities_10 = activities.entrySet()
                .stream()
                .filter(e->e.getValue()>10)
                .collect(Collectors.toList());

        try (PrintWriter writer = new PrintWriter("duration_activities.txt", "UTF-8")) {
            for (Map.Entry m : activities.entrySet())
                writer.println(m.getKey() + " " + m.getValue());
            writer.println("");
            writer.println("larger than 10:");
            for(Map.Entry entry : activities_10)
                writer.println(entry.getKey() + " " + entry.getValue());
            writer.close();
            System.out.println("Task_4: OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void task5() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Long> activities_5;
        activities_5 = data.stream()
                .filter(s->{try {
                    Date date1 = df.parse(s.getEndTime());
                    Date date2 = df.parse(s.getStartTime());
                    long diffInMillies = date1.getTime() - date2.getTime();
                    return (diffInMillies / 60000) < 5;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return false;
                }})
                .collect(Collectors.groupingBy(s->s.getActivity(),
                        Collectors.counting()));

        Map<String,Long> activities;
        activities = data.stream()
                .map(s->s.getActivity())
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        System.out.println(activities);
        System.out.println("<5\n" +activities_5);

        List<String> result;
        result = activities.entrySet().stream()
                .filter(a-> {List<Long> nr=activities_5.entrySet().stream().filter(s-> s.getKey().equals(a.getKey()))
                        .map(Map.Entry::getValue).collect(Collectors.toList());
                        return nr.size()==1 && nr.get(0)/a.getValue()>=0.9; })
                .map(a->a.getKey()).collect(Collectors.toList());

        System.out.println(result);

        try (PrintWriter writer = new PrintWriter("90%_activities.txt", "UTF-8")) {
            for (String i : result)
                writer.println(i);
                writer.close();
            System.out.println("Task_5: OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package ServerAdmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GlobalStats {

    @XmlElement(name = "stats_List")
    private List<GlobalStat> statsList;

    private static GlobalStats instance;

    private GlobalStats(){
        statsList = new ArrayList<GlobalStat>();
    }

    public synchronized  static GlobalStats getInstance(){
        if(instance == null){
            instance = new GlobalStats();
        }
        return instance;
    }

    public synchronized List<GlobalStat> getStatsList(){
        return new ArrayList<GlobalStat>(statsList);
    }

    public synchronized void addStat(GlobalStat stat) {
        statsList.add(stat);
    }

    public List<GlobalStat> getLastStats(int n){
        if(n<=0) return null;
        List<GlobalStat> copy = getStatsList();
        int size = copy.size();
        if(size<n) return copy;

        return copy.subList(size-n, size);
    }

    public double getAverageDelivery(long t1, long t2){
        if(t1>t2) return -1;
        List<GlobalStat> copy = getStatsList();
        if(copy.size()==0) return 0;
        List<Integer> val = copy.stream()
                .filter(s -> s.getTimestamp()>= t1 && s.getTimestamp()<=t2)
                .map(s-> s.getAverageDelivery())
                .collect(Collectors.toList());
        if(val.size()==0) return 0;
        double ret = (double) (val.stream().reduce(0, Integer::sum) )/ val.size() ;
        return ret;
    }

    public double getAverageKm(long t1, long t2){
        if(t1>t2) return -1;
        List<GlobalStat> copy = getStatsList();
        if(copy.size()==0) return 0;
        List<Double> val = copy.stream()
                .filter(s -> s.getTimestamp()>= t1 && s.getTimestamp()<=t2)
                .map(s-> s.getAverageKm())
                .collect(Collectors.toList());
        if(val.size()==0) return 0;
        double ret =  (val.stream().reduce(0.0, Double::sum)) / val.size();
        return ret;
    }

}

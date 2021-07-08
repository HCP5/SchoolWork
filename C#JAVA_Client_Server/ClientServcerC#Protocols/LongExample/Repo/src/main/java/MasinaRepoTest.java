import entities.domain.Masina;
import repository.database.HibernateMasina;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MasinaRepoTest {
    public static void main(String[] args) {
        HibernateMasina repo=new HibernateMasina();

        repo.save(new Masina("CJ06",1999));

        List<Masina> masinaList= StreamSupport.stream(repo.findAll().spliterator(),false).collect(Collectors.toList());
        masinaList.forEach(System.out::println);

        repo.delete(2);

    }
}

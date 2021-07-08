import domain.User;
import hibernatesRepo.Repository;
import hibernatesRepo.UserRepo;

public class TestRepository {
    public static void main(String[] args) {
        Repository<User,Integer> repository=new UserRepo();
        repository.delete(1);
        repository.findAll().forEach(System.out::println);
        repository.save(new User("hcp","hcp"));
        repository.findAll().forEach(System.out::println);
        repository.update(2,new User("hcpUpdated","hcpUpdated"));
    }
}

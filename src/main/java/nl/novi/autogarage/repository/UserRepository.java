package nl.novi.autogarage.repository;

import nl.novi.autogarage.model.User;
import org.springframework.data.repository.CrudRepository;

//String omdat de ID een string is "username"
public interface UserRepository extends CrudRepository<User, String> {
}

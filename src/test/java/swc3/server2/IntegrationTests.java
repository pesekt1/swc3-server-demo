package swc3.server2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import swc3.server2.model.Tutorial;
import swc3.server2.repository.TutorialRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationTests {

    @Autowired
    TutorialRepository repository;

    @BeforeEach
    public void init(){
        repository.deleteAll();
    }

    @Test
    public void should_find_no_tutorials_if_repository_is_empty(){
        Iterable<Tutorial> tutorials = repository.findAll();
        assertThat(tutorials).isEmpty();
    }

    //this could be in 3 separate tests
    @Test
    public void should_store_tutorial(){
        Tutorial tutorial = repository.save(new Tutorial("t1", "d1", false));
        assertThat(tutorial).hasFieldOrPropertyWithValue("title", "t1");
        assertThat(tutorial).hasFieldOrPropertyWithValue("description", "d1");
        assertThat(tutorial).hasFieldOrPropertyWithValue("published", false);
    }

    @Test
    public void should_find_all_tutorials(){
        repository.save(new Tutorial("t1", "d1", false));
        repository.save(new Tutorial("t2", "d2", false));
        repository.save(new Tutorial("t3", "d3", false));

        Iterable<Tutorial> tutorials = repository.findAll();
        assertThat(tutorials).hasSize(3);
    }





}

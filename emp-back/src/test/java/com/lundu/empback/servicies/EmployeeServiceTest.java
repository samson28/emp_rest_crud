package com.lundu.empback.servicies;

import com.lundu.empback.dto.mapper.EmployeeDTOMapper;
import com.lundu.empback.dto.request.EmployeeRequestDTO;
import com.lundu.empback.dto.response.EmployeeResponseDTO;
import com.lundu.empback.entities.Employee;
import com.lundu.empback.repositories.EmployeeRepository;
import com.lundu.empback.servicies.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeDTOMapper employeeDTOMapper;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository,employeeDTOMapper);
    }


    @Test
    void save() {

        // Préparer les données de test
        EmployeeRequestDTO employeeRequestDTO =  new EmployeeRequestDTO("Mariam","Jamel","F",25,70854628,"Mariam.jamel@gmail.com","DG");
        Employee employee = Employee.fromDTO(employeeRequestDTO);

        // Exécuter la méthode à tester
        employeeService.save(employeeRequestDTO);

        // Vérifier si la méthode save du repository est appelée avec l'employé correct
        verify(employeeRepository).save(employee);
    }

    @Test
    void showAll() {
        // Préparer les données de test
        List<Employee> employeeList = List.of(
                new Employee("Mariam","Jamel","F",25,70854628,"Mariam.jamel@gmail.com","DG"),
                new Employee("Alex","Alex","M",25,70986542,"Alex.jamel@gmail.com", "PDG")
        );

        // Configurer le comportement du repository mock pour retourner la liste d'employés
        when(employeeRepository.findAll()).thenReturn(employeeList);

        // Exécuter la méthode à tester
        List<EmployeeResponseDTO> result = employeeService.showAll();

        // Vérifier si la méthode findAll du repository est appelée
        verify(employeeRepository).findAll();

        // Vérifier si le mapper est appelé pour chaque employé
        verify(employeeDTOMapper,times(2)).apply(any(Employee.class));

        // Vérifier si la liste de résultats n'est pas vide
        assertNotNull(result);
        // Vérifier si la taille de la liste de résultats correspond à la taille de la liste d'employés
        assertEquals(employeeList.size(), result.size());

    }

    @Test
    void find() {
        // Préparer les données de test
        int id = 1;
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(1,"John", "Doe", "M", 30, 123456789, "john.doe@example.com", "Manager");
        Employee employee = new Employee("John", "Doe", "M", 30, 123456789, "john.doe@example.com", "Manager");

        // Configurer le comportement du repository mock pour retourner l'employé correspondant à l'ID
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        // Configurer le comportement du mapper mock pour retourner l'EmployeeResponseDTO correspondant
        when(employeeDTOMapper.apply(employee)).thenReturn(employeeResponseDTO);

        // Exécuter la méthode à tester
        EmployeeResponseDTO result = employeeService.find(id);

        // Vérifier si la méthode findById du repository est appelée avec l'ID correct
        verify(employeeRepository).findById(id);
        // Vérifier si le mapper est appelé avec l'employé correct
        verify(employeeDTOMapper).apply(employee);

        // Vérifier si le résultat correspond à l'EmployeeResponseDTO attendu
        assertEquals(employeeResponseDTO, result);
    }


    @Test
    void delete() {
        // Créez un exemple d'employé pour effectuer la suppression
        Employee employee = new Employee();
        employee.setId(1);

        // Mockez le comportement du repository pour vérifier si la méthode deleteById est appelée avec le bon ID
        doNothing().when(employeeRepository).deleteById(1);

        // Appelez la méthode delete du service en utilisant l'ID de l'employé
        employeeService.delete(1);

        // Vérifiez si la méthode deleteById du repository a été appelée avec le bon ID
        verify(employeeRepository).deleteById(1);
    }


    @Test
    void update() {

        // Préparer les données de test
        int id = 1;
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO("Alex","Alex","M",25,70986542,"Alex.jamel@gmail.com", "PDG");
        Employee employee =  new Employee("Jane", "Doe", "F", 25, 987654321, "jane.doe@example.com", "Employee");
        Employee updatedEmployee = new Employee("Alex","Alex","M",25,70986542,"Alex.jamel@gmail.com", "PDG");
        EmployeeResponseDTO updatedEmployeeResponseDTO = new EmployeeResponseDTO(1,"Alex","Alex","M",25,70986542,"Alex.jamel@gmail.com", "PDG");

        // Configurer le comportement du repository mock pour retourner l'employé correspondant à l'ID
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        // Configurer le comportement du mapper mock pour retourner l'employé mis à jour
        when(employeeDTOMapper.apply(employee)).thenReturn(updatedEmployeeResponseDTO);

        // Exécuter la méthode à tester
        EmployeeResponseDTO result = employeeService.update(id, employeeRequestDTO);

        // Vérifier si la méthode findById du repository est appelée avec l'ID correct
        verify(employeeRepository).findById(id);
        // Vérifier si l'employé est mis à jour avec les données de la requête
        employee.update(employeeRequestDTO);
        // Vérifier si la méthode save du repository est appelée avec l'employé mis à jour
        verify(employeeRepository).save(employee);
        // Vérifier si le mapper est appelé avec l'employé mis à jour
        verify(employeeDTOMapper).apply(updatedEmployee);

        // Vérifier si le résultat correspond à l'EmployeeResponseDTO attendu
        assertEquals(updatedEmployeeResponseDTO, result);
    }


    @Test
    void search() {
        // Créez un exemple de critères de recherche
        String searchQuery = "e";

        // Créez une liste d'employés correspondant aux critères de recherche
        List<Employee> employeeList = List.of(
                new Employee("Mariam","Jamel","F",25,70854628,"Mariam.jamel@gmail.com","DG"),
                new Employee("Alex","Alex","M",25,70986542,"Alex.jamel@gmail.com", "PDG")
        );

        // Configurer le comportement du repository mock pour retourner la liste d'employés correspondant à la recherche
        when(employeeRepository.searchEmployeeByNomOrPrenom(searchQuery)).thenReturn(employeeList);

        // Exécuter la méthode à tester
        List<EmployeeResponseDTO> result = employeeService.search(searchQuery);

        // Vérifier si la méthode searchEmployeeByNomOrPrenom du repository est appelée avec les paramètres corrects
        verify(employeeRepository).searchEmployeeByNomOrPrenom(searchQuery);

        // Vérifier si le mapper est appelé pour chaque employé
        verify(employeeDTOMapper, times(employeeList.size())).apply(any(Employee.class));

        // Vérifier si la liste de résultats n'est pas vide
        assertNotNull(result);
        // Vérifier si la taille de la liste de résultats correspond à la taille de la liste d'employés
        assertEquals(employeeList.size(), result.size());
    }

}
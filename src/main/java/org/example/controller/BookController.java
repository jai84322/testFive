package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.BookEntity;
import org.example.exception.CustomException;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    // create books
    @PostMapping("/createBook")
    public BookEntity createBook(@RequestBody BookEntity bookEntity) {
        bookRepository.findById(bookEntity.getId()).ifPresent(existingBook -> {
            throw new CustomException("book already present", HttpStatus.CONFLICT);
        });
        return bookRepository.save(bookEntity);
    }

    @PostMapping("/createBookInList")
    public List<BookEntity> createBookInBulk(@RequestBody List<BookEntity> bookEntities) {
        for (BookEntity bookEntity : bookEntities) {
            bookRepository.findById(bookEntity.getId()).ifPresent(existingBook -> {
                throw new CustomException("book already present", HttpStatus.CONFLICT);
            });
        }
        return bookRepository.saveAll(bookEntities);
    }

    // return books before 2000
    @GetMapping("/getBooksBefore2000")
    public List<BookEntity> getBookBefore2000 () {
        return bookRepository.findAll().stream()
                .filter(obj -> Integer.valueOf(obj.getReleaseYear()) > 2000)
                .collect(Collectors.toList());
    }

    @GetMapping("getAllBooks")
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/getSpecificBook/{bookId}")
    public BookEntity getSpecificBook(@PathVariable("bookId") Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new CustomException("book not found", HttpStatus.NOT_FOUND));
    }

    @PutMapping("/updateBook/{bookId}")
    public BookEntity updateSpecificBook(@PathVariable("bookId") Integer bookId, @RequestBody BookEntity bookEntity) {
        return bookRepository.findById(bookId).map(obj -> {
             obj.setAuthorName(bookEntity.getAuthorName());
             obj.setReleaseYear(bookEntity.getReleaseYear());
             return bookRepository.save(obj);
        }).orElseThrow(() -> new CustomException("book id is wrong and not found"));
    }

    @DeleteMapping("/deleteAllRecords")
    public ResponseEntity<String> deleteAllRecords() {
        bookRepository.deleteAll();
        return ResponseEntity.ok("All records deleted successfully.");
    }

    @DeleteMapping("/deleteSpecificId/{bookId}")
    public ResponseEntity<String> deleteSpecificId(@PathVariable("bookId") Integer bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return ResponseEntity.ok("Book deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
    }

    @PostMapping("/testTransactionalAnotation")
    public BookEntity testTransactionalAnnotation(@RequestBody BookEntity bookEntity) {
        bookRepository.findById(bookEntity.getId()).ifPresent(existingBook -> {
            throw new CustomException("book already present", HttpStatus.CONFLICT);
        });
        return bookService.createBookAndUpdateStock(bookEntity);
    }

    // Array: Fixed size, can hold elements of the same type (primitive or objects).
    //List: Dynamic size, part of the Java Collections framework,
    // and can grow/shrink as needed. It only holds objects, not primitives.

//    In an array: It can only hold elements of the same type,
//    meaning you can't store a mix of different types (e.g., numbers and strings together).
//    For example, an int[] can only hold integers, and a String[] can only hold strings.
//    In a List: Yes, a List can store different types of objects together (e.g., List<Object> can hold both numbers and strings).
//    However, if you use a specific type like List<Integer>, it can only hold Integer objects.
    @GetMapping("/convertArrayToList")
    public void convertArrayToList() {
        // trying to convert static array to array list type
        // method 1
        String[] array = {"a", "b", "c"};
        List<String> list = Arrays.asList(array);

        // method 2 -> stream in java 8
        int arrayTwo[] = {1,2,3,4,5};
        List<Integer> listTwo = Arrays.stream(arrayTwo).boxed() .collect(Collectors.toList());
    }

    @GetMapping("/convertListToArray")
    public void convertListToArray() {
        // convert list to array
        // method 1
        List<String> list = Arrays.asList("a", "b", "c");
//        List<String> list = List.of("a", "b", "c"); // another way to create list
        String[] array = list.toArray(new String[0]);

        // method 2 using java stream
        String[] arrayTwo = list.stream().toArray(String[]::new);
//        String[] arrayTwo = list.toArray(new String[0]); // another syntax
//        Integer[] array = list.toArray(new Integer[0]); // for integer

    }

    public List<Object> getArrayAsList() {
        List<Object> list = Arrays.asList("A", "1", "2", "b");
        return list;
    };

    @GetMapping("arrayMethodsOne")
    public void arrayMethods() {
        // array.asList()
        // used to create a fixed list
        // used to convert array to list
        // return list
        List<Object> list = getArrayAsList();
        System.out.println("asList: " + list);

//        convert this list of object to string type list -> method 1
        List<String> ObjectListToStringListOne = list.stream()
                .map(Object::toString)
                .collect(Collectors.toList());

//        convert this list of ojbect to string -> method 2
        List<String> ObjectListToStringListTwo = new ArrayList<>();
        for (Object obj : list) {
            ObjectListToStringListTwo.add(obj.toString());
        }


    }

    @GetMapping("arrayMethodsTwo")
    public void arrayMethodsTwo() {
        List<String> test = new ArrayList<>();
        test.add("hello");
        test.add("testing");
        test.add("checking");
        test.add("making");
//        test.stream().forEach(System.out::println); // print the list

        Boolean bl = test.contains("mak"); // false: contains will do exact matching only
        System.out.println("bl: " + bl);

        String str = test.get(1); // will return element on 1st index
        System.out.println("get method: " + str);

        test.sort(Comparator.naturalOrder());
        System.out.println(test);

        test.sort(Comparator.reverseOrder());
        System.out.println(test);

    }

    @GetMapping("/streamsApi")
    public void practiseStreams() {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // print all elements
        Stream<Integer> stream = numbers.stream();
        stream.forEach(System.out::println); // to loop over all elements
//        stream.forEach(System.out::println); // will throw illegalStateException

        // filter out elements using stream
        List<Integer> evenNumber = numbers.stream()
                .filter(n -> n%2 == 0)
                .collect(Collectors.toList());
        System.out.println("evenNumber: "+evenNumber);

        // map and return number *2
        List<Integer> mappedValues = numbers.stream()
                .map(n -> n*2)
                .collect(Collectors.toList());
        mappedValues.forEach(System.out::println);

//        another syntax to print
        numbers.stream()
                .forEach(n -> System.out.println(n));  // Prints each number in the list

        // sorting
        List<Integer> sorted = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sorted);  // Output: [1, 2, 3, 4, 5]

        // Optional: Streams can return an Optional result.
        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 3)
                .findFirst();
        System.out.println(first.orElse(0));  // Output: 4

        // Chaining Streams
        List<Integer> result = numbers.stream()
                .filter(n -> n > 2)
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println(result);  // Output: [6, 8, 10]

        // reduce
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);  // Output: 15

//        Intermediate vs. Terminal Operations
//        Intermediate operations (like map(), filter()) return a new stream.
//        Terminal operations (like collect(), forEach()) trigger the processing and return a result.

    }

    @GetMapping("hashMapMethods")
    public void testMapMehtods() {
        HashMap<Integer, String> testMap = new HashMap<>();
        testMap.put(1, "testing");
        testMap.put(2, "new testing");
        testMap.put(3, "third testing");

        System.out.println(testMap);
        System.out.println("here: "+testMap.get(1));
        System.out.println("here new: " + testMap.entrySet());
        System.out.println("here 3: " + testMap.keySet());
        System.out.println("here 4: " + testMap.values());
        System.out.println("here 5: " + testMap.containsKey(4));
        System.out.println("here 6: " + testMap.containsKey(2));

        //ways to loop over a map -> do this
        testMap.forEach((k,v) -> {
            System.out.println("key: " + k + "values: " + v);
        });

        // second way -> do this
        for (Integer key : testMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + testMap.get(key));
        }

        // 3rd way but will pring only values
        for (String value : testMap.values()) {
            System.out.println("Value: " + value);
        }

        // 4th way - don't get into it
        Iterator<Map.Entry<Integer, String>> iterator = testMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // 5th way -> do this
        for (Map.Entry<Integer, String> entry : testMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        Map<String, Integer> map = Map.of("One", 1, "Two", 2, "Three", 3);
        System.out.println(map); // Output: {One=1, Two=2, Three=3}

        // set m doing here
        Set<String> set = Set.of("A", "B", "C");
        System.out.println("hashSet: " + set); // Output: [A, B, C]

    }


    @GetMapping("jsonNodeTesting")
    public void testJsonNOdeMethods() throws JsonProcessingException {

        String jsonString = "{\n" +
                "  \"id\": 1,\n" +
                "  \"bookName\": \"The Great Gatsby\",\n" +
                "  \"authorName\": \"F. Scott Fitzgerald\",\n" +
                "  \"releaseYear\": \"1925\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString); // userObj directly from db

        // Define the target type using a TypeReference
//        TypeReference<List<MyObject>> typeReference = new TypeReference<List<MyObject>>() {};

        // Convert the JsonNode to a list directly
        BookEntity myList = mapper.convertValue(jsonNode, new TypeReference<>() {});
        BookEntity myListTest = mapper.convertValue(jsonNode, BookEntity.class);
        System.out.println("my list: " + myList); // working
        System.out.println("myListTest: " + myListTest); // working


    }

}

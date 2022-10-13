package sk.ness.academy.springboothibernate.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import sk.ness.academy.config.TestDataSourceConfig;
import sk.ness.academy.springboothibernate.dao.BookDao;
import sk.ness.academy.springboothibernate.model.Book;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = { TestDataSourceConfig.class, BookDaoImpl.class })
@Transactional
@Sql({"/initdb.sql"})
class BookDaoImplTest {

  @Autowired
  private BookDao bookDao;

  @BeforeEach
  public void beforeEach() {
    System.out.println("### BeforeEach ###");
  }

  @Test
  void findAllTest() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertEquals(3, books.size());
  }

  @Test
  void testId() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertEquals(1, books.get(0).getId());
    Assertions.assertEquals(2, books.get(1).getId());

  }

  @Test
  void testNameLength() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertEquals(6, books.get(0).getName().length());
    Assertions.assertEquals(6, books.get(1).getName().length());

  }

  @Test
  void testFirstChar() {
    final List<Book> books = bookDao.findAll();
    Assertions.assertTrue(books.get(0).getName().startsWith("B"));
    Assertions.assertTrue(books.get(1).getName().endsWith("2"));

  }


}
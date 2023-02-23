package org.cefire.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookCollectionTest {

    @Test
    public void ShouldFindBookByISBN(){
        final String isbnToLocate = "un-isbn-2";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un título 1","un autor 1"),
                new Book(isbnToLocate,"un título 2","un autor 2"),
                new Book(isbnToLocate,"un título 2","un autor 2"),
                new Book("un-isbn-3","un título 3","un autor 3")
        });

        List<Book> foundBooks = books.find(isbnToLocate);
        Assertions.assertFalse(foundBooks::isEmpty);

        foundBooks.forEach((Book book) -> Assertions.assertEquals(isbnToLocate,book.getISBN()));
    }

    @Test
    public void shouldGetAnEmptyListIfNoMatchesAreFound(){
        final String isbnToLocate = "un-isbn-no-existe";
        final String titleToLocate = "un título X";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un título 1","un autor 1"),
                new Book("un-isbn-2","un título 2","un autor 2"),
                new Book("un-isbn-3","un título 3","un autor 3")
        });

        Assertions.assertTrue(books.find(isbnToLocate).isEmpty());
        Assertions.assertTrue(books.find(titleToLocate).isEmpty());

    }

    @Test
    public void ShouldFindBookByTitle(){
        final String partialTitleToLocate = "un títu";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un título 1","un autor 1"),
                new Book("un-isbn-2","un título 2","un autor 2"),
                new Book("un-isbn-2","un título 2","un autor 2"),
                new Book("un-isbn-3","un título 3","un autor 3")
        });

        List<Book> foundBooks = books.find(partialTitleToLocate);
        Assertions.assertFalse(foundBooks::isEmpty);

        foundBooks.forEach((Book book) -> Assertions.assertTrue(book.getTitle().contains(partialTitleToLocate)));
    }


    @Test
    public void shouldGetNoMatchesIfBookCollectionIsEmpty(){
        BookCollection books = new BookCollection(new Book[]{});

        List<Book> foundBooks = books.find("cualquier-parametro-de-busqueda");

        Assertions.assertTrue(foundBooks::isEmpty);
        Assertions.assertEquals(foundBooks.size(),0);   // una altra forma del fer el mateix

    }

    @Test
    public void shouldFindCopiesOfABook(){
        final Book bookToBeFound = new Book ("isbn-que-debe-encontrarse","titulo1","autor1");
        BookCollection books = new BookCollection(new Book[]{
                new Book("isbn-que-debe-encontrarse","titulo1","autor1"),
                new Book("un-isbn-2","un título 2","un autor 2"),
                new Book("un-isbn-2","un título 2","un autor 2"),
                new Book("isbn-que-debe-encontrarse","titulo1","autor1"),
                new Book("un-isbn-3","un título 3","un autor 3")
        });

        List<Book> foundBooks = books.findCopies(bookToBeFound);
        Assertions.assertFalse(foundBooks.isEmpty());
        foundBooks.forEach((Book book) -> Assertions.assertEquals(book,bookToBeFound));
    }
}

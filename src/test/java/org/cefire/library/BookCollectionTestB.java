package org.cefire.library;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookCollectionTestB {  //TestB és igual que l'original però important directement els asserts
                                    // i estalviant aixina el Assertions. cada vegada

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
        assertFalse(foundBooks::isEmpty);

        foundBooks.forEach((Book book) -> assertEquals(isbnToLocate,book.getISBN()));
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

        assertTrue(books.find(isbnToLocate).isEmpty());
        assertTrue(books.find(titleToLocate).isEmpty());

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
        assertFalse(foundBooks::isEmpty);

        foundBooks.forEach((Book book) -> assertTrue(book.getTitle().contains(partialTitleToLocate)));
    }


    @Test
    public void shouldGetNoMatchesIfBookCollectionIsEmpty(){
        BookCollection books = new BookCollection(new Book[]{});

        List<Book> foundBooks = books.find("cualquier-parametro-de-busqueda");

        assertTrue(foundBooks::isEmpty);
        assertEquals(foundBooks.size(),0);   // una altra forma del fer el mateix

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
        assertFalse(foundBooks.isEmpty());
        foundBooks.forEach((Book book) -> assertEquals(book,bookToBeFound));
    }

    @Test
    public void shouldGetExceptionWhenUsingFindOrFailWithoutExistentEntry(){
        final String textToLocate = "isbn-o-autor-que-no-existen";
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un título 1","un autor 1"),
                new Book("un-isbn-2","un título 2","un autor 2"),
                new Book("un-isbn-2","un título 2","un autor 2"),
                new Book("un-isbn-3","un título 3","un autor 3")
        });

        // açò fa el mateix que el try-catch de baix
        assertThrows(BookCollection.ExpectedToFindAtLeastOneBook.class, () -> books.findOrFail(textToLocate));
//        try{
//            books.findOrFail(textToLocate);
//            Assertions.fail("Expected exception but got none");
//        }
//        catch(Exception e){
//            Assertions.assertInstanceOf(BookCollection.ExpectedToFindAtLeastOneBook.class,e);
//        }
    }
}

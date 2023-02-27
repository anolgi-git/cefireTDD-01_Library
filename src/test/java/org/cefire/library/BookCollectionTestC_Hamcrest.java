package org.cefire.library;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;

public class BookCollectionTestC_Hamcrest {  //TestC és igual que TestB però utilitzant la llibreria de Hamcrest

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
        assertThat(foundBooks.isEmpty(), is(false));

        foundBooks.forEach((Book book) -> assertThat(isbnToLocate, is(equalTo(book.getISBN()))));
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

        assertThat(books.find(isbnToLocate).isEmpty(),is(true));
        assertThat(books.find(titleToLocate).isEmpty(),is(true));

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
        assertThat(foundBooks.isEmpty(),is(false));

        foundBooks.forEach((Book book) -> assertThat(book.getTitle().contains(partialTitleToLocate),is(true)));
    }


    @Test
    public void shouldGetNoMatchesIfBookCollectionIsEmpty(){
        BookCollection books = new BookCollection(new Book[]{});

        List<Book> foundBooks = books.find("cualquier-parametro-de-busqueda");

        assertThat(foundBooks.isEmpty(),is(true));
        assertThat(foundBooks.size(),is(equalTo(0))); // una altra forma del fer el mateix
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
        assertThat(foundBooks.isEmpty(),is(false));
        foundBooks.forEach((Book book) -> assertThat(book,is(equalTo(bookToBeFound))));
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

        // assertThrows no te un equivalent en Hamcrest, aixina que ho deixem igual
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

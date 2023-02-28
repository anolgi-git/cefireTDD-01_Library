package org.cefire.library.bookcollection;

import org.cefire.library.Book;
import org.cefire.library.BookCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindOrFailTest {
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

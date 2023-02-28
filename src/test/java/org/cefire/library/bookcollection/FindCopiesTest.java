package org.cefire.library.bookcollection;

import org.cefire.library.Book;
import org.cefire.library.BookCollection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FindCopiesTest {
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
}

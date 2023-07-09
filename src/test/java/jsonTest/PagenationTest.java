package jsonTest;

import org.junit.Test;
import org.publicwifi.v1.others.Pagenation;

public class PagenationTest {


    @Test
    public void pagenationTest() {

        long totalCount = 127;
        long pageIndex = 12;

        Pagenation pagenation = new Pagenation(totalCount);
        System.out.println(pagenation.html(pageIndex));
    }
}

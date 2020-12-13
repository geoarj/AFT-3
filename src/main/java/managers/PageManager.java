package managers;

import pages.BucketPage;
import pages.ProductPage;
import pages.ResultsPage;
import pages.StartPage;

/**
 * Менеджмент страничек
 */
public class PageManager {

    private static PageManager pageManager;
    private StartPage startPage;
    private ProductPage productPage;
    private ResultsPage resultsPage;
    private BucketPage bucketPage;

    private PageManager() {
    }

    public ResultsPage getResultsPage() {
        if (resultsPage == null) {
            resultsPage = new ResultsPage();
        }
        return resultsPage;
    }

    public static PageManager getManagerPages() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    public BucketPage getBucketPage() {
        if (bucketPage == null) {
            bucketPage = new BucketPage();
        }
        return bucketPage;
    }
}

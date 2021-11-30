public class Substring {

    public static void main(String[] args) {
        String site="www.Nadya-Ymnitsa-Krasavitsa.com";
      site=site.substring(site.indexOf(".")+1);
      site=site.substring(0,site.indexOf("."));
        System.out.println(site);
    }
}

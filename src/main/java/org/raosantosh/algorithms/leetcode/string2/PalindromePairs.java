package org.raosantosh.algorithms.leetcode.string2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

 Example 1:
 Given words = ["bat", "tab", "cat"]
 Return [[0, 1], [1, 0]]
 The palindromes are ["battab", "tabbat"]
 Example 2:
 Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePairs {

  public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    Map<String, Integer> wordIndexMap = new HashMap<String, Integer>();
    for(int i=0; i < words.length; ++i) {
      wordIndexMap.put(words[i],i);
    }

    for(int i=0; i < words.length ; ++i) {

      for(int j=0; j <= words[i].length(); ++j) {

        String prefix = words[i].substring(0, j);
        String suffix = words[i].substring(j);

        if(isPalindrome(prefix)) {
          String reverseSuffix = new StringBuilder(suffix).reverse().toString();
          Integer resultIndex = wordIndexMap.get(reverseSuffix);
          if(resultIndex !=null && resultIndex != i ) {
            result.add(Arrays.asList(resultIndex, i ));
          }
        }

        if(isPalindrome(suffix) && suffix.length() > 0) {
          String reversePrefix= new StringBuilder(prefix).reverse().toString();
          Integer resultIndex = wordIndexMap.get(reversePrefix);
          if(resultIndex !=null && resultIndex != i ) {
            result.add(Arrays.asList( i, resultIndex));
          }
        }
      }
    }


    return result;
  }

  public List<List<Integer>> palindromePairsBruteForce(String[] words) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    for(int i= 0 ; i < words.length; ++i) {
      for(int j=0 ; j < words.length; ++j) {
        if(i != j ) {
            if(isPalindrome(words[i] + words[j])) {
              result.add(Arrays.asList(i, j));
            }

        }
      }
    }

   return result;
  }

  boolean isPalindrome(String word1) {
    //System.out.println(word1);
    for(int i=0 ; i< word1.length()/2; ++i) {
      if(word1.charAt(i) != word1.charAt(word1.length() - 1-i))
        return false;
    }
    return true;
  }

  public static void main(String args[]) {
    PalindromePairs pairFinder = new PalindromePairs();
    System.out.println(pairFinder.palindromePairs(new String[] {"bat", "tab", "cat"}));
    System.out.println(pairFinder.palindromePairs(new String[] {"abcd", "dcba", "lls", "s", "sssll"}));
    System.out.println(pairFinder.palindromePairs(new String[] {"a", ""}));

    //System.out.println(pairFinder.palindromePairs(new String[] {"cbcdfiagbbhfbdb","iaebjjchhegdcih","fjaecjijdcdbdbfdb","gacchccbiejeheddbdji","ggachhhj","j","dbhgiee","i","fdg","dagbfdj","aejbed","dcibjcebgj","d","ajefedadiageeae","hfcbjec","eahbc","hggeb","cghbeaahjifgejah","hafgjacjadcehj","fifacef","dffg","hiabiic","facdabebbiadaa","hacabfcgaagaddac","eijeejhhggccfccfd","caighh","cahifhjdaachhjfbehj","bijgfgbfjaejgccehh","fjaehiidie","bbdefedcdgdfjihic","dggecieeec","ieaibdhfgfbfhafci","jhghjfjdebehdjahjcd","gafhcja","gdbbgihjgjjgb","digedajahe","ef","fjidaejffhach","bdi","ifdj","hfbbjichhfg","efdcfbiefjjgadafhfji","eiibeceiie","bbefbeidiahid","hihijecbjghhafjh","aibjjdd","hhgagfbhfdcjcebfdig","fdfefiaeccdfihgab","jhadeddd","ijghgbdejdhgi","heiecgh","daefjjhehjhcdecja","jdhfgdedf","bgfi","dh","ciifefgie","ceibbhabaigdcdihfd","ijaeaghaacijeeh","dihaideibcdbdaadeibg","cghjeeaabegcfhb","fhigjdfccbbebfhjccfb","ifcbjcjijhefbechhige","cacbcgfdfieciijbj","bjjefhbdbfb","jbibahe","djhfagbedaihegcdefa","fhdgbicifdagahchahg","afgfhgjdiebeecegjgh","cdcfafdibjhccdhbjf","gddhahggjbdj","efcgbg","cihgefjfe","edafdhddbbgdhebj","bjjhhafbffbea","fgeafdcde","babdihifchh","f","effeedhdeh","ejfaeffddfgiedfahbf","fdjcffaaffhgaef","ccedddaajf","jbhfhhefja","hbidebajchhihhf","hfbjigbcfbg","baabc","ccccd","dahigffgb","agfbjadh","jjafjddhhadeeabajef","iibegj","eddgjgg","afdheeedjbbf","dijhefbbihf","chehadgbfagdebbcbh","cje","ca","fdedhchcbajhibc","ebjadhjacchbejh","gcjihgbcffjj","cejhcghiiiicibcebc","ajjgfchfjbiibfajb","jfdfh","jbhgcf","hdbhbcgeb","ijc","hfjhajeecijfbgabhd","hijhjhdgggiceigjgi","eahiga","cdjigcaej","cehhffcbhhgfdaf","hgf","iiifghee","jbbjbdeihdcchjjda","jjfdeabe","jiehbfcijfahafgibf","jebdfgefbdchejdcbe","ba","fhijejhbbjjc","diacjghgdj","hgfabcc","icjhjaedafbi","dejbbfa","cfhgjjccjebeijga","abfbgijfgade","fhegfiehhe","jehdejfegfcigeid","gdfibijdeh","jej","cdgicbabfg","afa","bbjcfcaefe","bcjdchejhdfegadda","ebacafg","bggefc","jfcec","gibfigb","dgjfcahaecjhhbeg","geccgfjgfh","gjghcfejdgeea","gfai","hhbjiajegaga","fffdgebajcgjf","cjcdfbjfe","eaedjf","ihagdidagggddcgiajc","efccfgejjegbiag","jdjcd","geg","bddjjbedadcdcgj","beea","bafdgceghjffgfedcdhj","jd","eeabggi","dbjhfgjcffd","ddcabdfcigbdgbbjdgf","geghjdiifabbbed","dabd","ce","hcjhhefdeffiiiicchf","eea","bjcajgeieb","fgcabh","agbjb","hfiajfjdbiddg","dgii","feijfai","cgcbbiici","hgchffa","hdefjdhccfcei","gddeadhhehhdbabhi","eehajcgjdcf","jh","djecdicjf","chadcdejaj","egebia","bejibijc","ccgabfgachjj","ffhibei","bhh","hc","chdbcejhiabfcbifachc","ibjfhai","dabibgcahafebhbchge","jadhjabhaceaaceg","dcgbbjhigihaejfb","dfhbigaeacgfgagfdege","hjjfddadgeeedhfifjd","di","dgifja","ghjeehbhge","fejicig","fabibcedehe","fjighjfiiiaig","eba","gjjaifgjedgccabbf","adje","aa","ijcjjdihdfbcah","edaiifiica","jiabebeejifbdaai","iacjjidfegfheh","if","bgc","hjeeggegidcfhfafc","ghg","cajdbbe","aj","aidjhiie","fcgdcdcbgjddccdi","aaafghgefiaggjdeif","bfdegiefgbcjecdbg","adabha","ihg","ffedjh","edhajcfidicehifjcghf","ajbfichedejeggiace","ibjabjfejfbchcaibdbg","ifiichf","b","aeaedgibggah","igdf","djecdfcfebci","bgigfbcidaecic","e","bccf","hdiggbccaec","djcegbdga","iibddcfdiigg","gfieg","bbjefefjgda","eibgeccfhicffjaajhbe","ahe","gcfggegdggjgjga","ifhcbefaebcghae","jhd","ebeddibg","dfcefebgcbjghieee","hfcdihdeaiaaig","ajhgafebabjjhhhh","icbjeadgfeijbjjje","jigahffbffcihgdjaehc","fcijicdfc","hgahgabihdbgaghadeja","ccjdfgaghiadcicegdhb","djdidehcbhbaabejdeac","g","bjejaachfffjgcjcgah","abfecdjdhdih","bjbaae","dfcej","gjfeacgfabjfhded","jigdbiiaidacgghg","ecdjgajdjiedjfghadai","ddgfjefbgbgfadajde","geahgchd","dac","eafbdahifhgidjahhdc","jdchideabfabgidbcaf","iaddfedjigdhfeb","fhi","hjcc","dehefdcdf","acdchdfgf","eghhffgbfgibeehdi","cfefcihgdafjadc","eefegfiaaaeae","gbgcjiffgbeagd","ceagbigehd","jafajgadehdiecdejff","eahaahadddhia","igbdfgjcghhabebebiaf","effciadaj","adcgeh","feecgbgbhegeh","fddhibhd","dghchfghdgcdjdajjhaa","jfjhfjgeegjf","dcaheadhfhciicf","dc","jbcfggai","ihc","ghfea","idg","aidgcgaigddjgec","fbgi","djgbhhjbbigcj","fheaibh","gggifgfcebigeehicfaa","aihjefggahifeffdjdd","cbafidfdagiegdjhdbj","eebfiifjgedfa","bfihcdac","ejaahfjhajegajfadcda","bbe","fhbdia","hdgdahgifjbdic","jiaacaifijfegjdfjgf","gihbhd","gdaffjiece","aegdagdaaagjfhhb","ieiefddafjbidb","bdhffdcff","hjfgeb","agiagbjaigfde","ajaghahibgbagabd","jgadddfgig","gbdcahjidjai","chge","iabbecaifd","ecde","ehdidcchacaeiaccf","ecbjbjficdji","jgdjcgbchbdfag","cfhbjg","fgjca","geeihfadeh","dhijieccibai","efdbgcidddgdehdeai","djia","ffaacihcfbagijgga","gicehdghhgdcda","bgg","aiddfdfihbh","dbhafchafajcj","hjhdaeabb","bb","ej","cifhcafdjahcgf","aabgigjefd","cfibiaagehgafaicfid","edbhgeh","haec","ecibgighfdcbie","efg","adhbeehcfhdhde","ji","gfjcdaidcefabjfi","ghdabahbgabjcbffaa","gibiha","dcj","iecc","aaaciidihbaiacefi","cfhfacabcfhdb","ehccjffddhaiidej","ajehfce","jdfahahcdb","jbca","hfhcjaeaaebijj","dheicebeijafahbafjb","jiidcidabcijhc","iegbhcjd","bai","hhbica","fcchfhafeib","gjiici","dieaggifhdbjbfebaidi","hcahiadcibafddi","acgfgjgccjdhdgagacb","fhfiiiadfbdjichhejd","dhcgeaeagidcbbihb","gdbjbebbgc","hdfieh","agifaebibhjggbah","hafgaaaicdaaabjfj","ahjbdfihg","fijbdidjjjedcjb","ggeejbjbhcd","fghhca","feajdigehdibag","ebjjdhdhajhdcgfeid","jgbhfgaajffd","dcgi","gifjcfbgbjhbcbh","bihafhccaihh","djdjbciagebjgfgcbcid","gdajchficfajcggdddgh","gffihac","ijhgacdhi","adebabfjgcdcihbhcbdh","bhffaiijhcediebfif","ccijfejheidfdgf","fiafc","dabdfgiieb","hdfjbghejbf","ejfbc","ieh","jdfjih","checj","aaahihejidiiiffijdhh","ddfighfdcghiegjid","agbejigicdbfgjbiff","hfa","ihfhcg","fhicgjfjcffibddhj","eajhacigijfiiajbfaah","hh","adff","jicedff","gd","jfgbjhijchegadcf","biha","acgeaehfhjciaeje","gghdjcihhcdgcb","feg","adfdfaa","bccdejhhjgcbj","jgfcj","ifa","cccif","ihaaiadfhahah","hciefifdajdaeibbji","cibgjjchfibfbfghie","fg","jggibh","achgf","hiag","adajjedbhf","cge","ighfebfghdg","dggjhgec","jcfbcccibhcbacf","beieg","jigijagefjdijjggida","jbcefgbdfjbeaicbhh","fjfjeaedbfhgedaecch","jdfgbcgceecdjdbbac","ibehcfcacd","dbi","bdfjdhcfbjbhadajjjj","hfahcfihbhdaafgcffe","chahbf","fdhijcecahheej","jiciadh","ebcbahbecc","eggf","cjheaic","abhie","cicbj","bdjjagcabfja","eeeajiajaa","cccjijbfd","gbhggdbebjacgh","ciddgjijeafiabebbjf","hdgaehfbfdecabagadd","ajbhdhghiaefbcdegch","ge","cjbfiaefghjhjebihag","eeh","igffiiihbefidc","fbejegfgeaahgghebjj","cfidj","ejjadab","ggfjiffia","ggddcijihjajgjahia","bfaegcjbidgdi","fa","baccaadid","effaheaeegf","deahhgfddb","fbbigafijahigahef","cccbihejifdi","gbbgiadjfjbffejfd","gedjigbgidbhbeii","ejaeehaejddcahhgabd","iiidjhfci","biibebdjcgijijfiaji","ciaf","bhiihaihcefjehf","dajfegjd","ejif","dbddiichf","eh","bfdfdcdfcfh","ghdhfjaccabhdddi","bhfjiahiffaddde","ehihahabhfbhjieg","egdh","faje","gdceaegiha","ghhjiadeb","hidfecjaahc","bdf","jdcejjhbdachhhdbh","gihgcjjijg","ghfag","cddgejjgafjcgdhie","c","bcjccijgccegehid","fcgdghjbjdjccebgaj","jaehdjdddbgaaihbef","gdichbcdgacafee","hcacjeieahgjecdhhia","hj","aachbibdib","igdejbfdbchgeeg","ejae","hicjfhicjhfiaccfjhb","hebeaf","abdjhcaeaffebiehidf","ecighbhbjdgaggfdcccf","hgfddcd","dcaieafbfb","hfghdbacdheaeaejg","cjaejagjedaiehh","eid","giij","babhjgdhfcch","jjdi","fcbhiiejgedfidigd","gbjiacgghceehggdhe","cdbdabcggiihgc","agdacjejgdg","gjcabceciidiejadj","hdcegbaej","aafh","feij","iicefb","gbchd","ifbabfb","jijcaigbdehdedacgjgh","fbeebegbad","agdhijabhabifd","hbjfjfigjebehabif","cj","eidhjc","fhac","h","eiciica","fbggeiifddchaicddcgj","ejggjbjefbfejacgi","egc","edgjfaegajhifj","cicehjehf","eeibjdhjeghbff","fjghjeejccbidhchdcaj","didgjfj","feh","fibbiid","ghdfhieiheefbfh","ifbjfaah","cjfaiieccgjgdggae","ddbhjicbgfgjifd","gdeccggd","dgjaeabdjecfdcfaag","ihjcdggbffbgeihfaefd","fbeijhj","fbdjdbghdjfh","ghaaijgifagccijeh","dhaaccbbgaedeehcfe","ficegih","ibbecegifjhhbefjcdfh","ejafcbejiceahjjabjgg","igg","ahgabd","gjcfiefgafhedf","achfjg","eebdh","bbbjeafbegc","fciaacgcbebhgbhchb","ahhigje","ddfcjjbfcfafiif","cghc","jbibgjbhg","iggjihbbgcidjhai","idjcbag","bdjahgccgeihcc","edhfidajecjadii","ifggbc","iiafdf","hgih","dbiejiiaedhgbdhf","ffedehceach","hbjibiiibadfbgjjhf","dfaibfhjjddehc","ijbidcahah","bbb","hhbbgf","ibgidhhjgddejfjba","gegcebabbhbd","caffeebgajbcb","ifdgfegehj","hiaae","iehbccfegih","ibgjejib","fjfjgcciifgccah","fchfajjcjcdbjc","djejidabejb","ifahfiaabdjgadgjc","fhbbidab","dbahbfg","bcciabbhjicibaaabge","fcafa","ga","gfjaabfaagbhaddce","dheeaahabfceijc","hcjffhf","efiicjjbgbchaieeag","hdfjhabbagfdgci","iebiddjcdcd","ccfhcijecbjgadjj","ed","eebac","hhffcf","ejh","cibhbcgcfgi","daabe","ajhijeggfa","iihfbggchdaji","hf","agadedba","gcjiaaaiggagg","a","bjajj","gccgiaifhadgg","gjccjfhccji","jdcecgjfdbfeiaefeddi","ccbhdhiffiibb","iagghdcbffchiegggii","iddieddgahebed","cdhaecg","cadegbjbhhge","ddhaiccjafhagff","daaddbefhh","fijjgdfeffeaecgjdgg","bif","faij","ecfcbabaajdidaghech","hiiaibccgbjjfbjbai","cafidfdjccecbcg","iebhhheaaaeafbcgi","bdbjjijbagic","gfghejbjfhhhfcfaeb","ecagbi","bacfbffdcdhbhgb","ehghdbehhjdjhec","ghdjgbddihafeebg","ihdfaidfccgccdjd","fgegddhejcbafjec","fdh","ejhfbdceeicedbdififg"}));
  }

}

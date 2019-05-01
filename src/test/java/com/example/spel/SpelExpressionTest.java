package com.example.spel;

import com.example.domain.Department;
import com.example.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.*;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spel/application.xml"})
//@PropertySource("classpath:application.properties")
@ActiveProfiles("dev")
public class SpelExpressionTest {

    @Autowired
    private Product product;

    @Autowired
    private Department department;

    private ExpressionParser parser;

    @Before
    public void before() {
        parser = new SpelExpressionParser();
    }

    /**
     * 在SpEL裡面可以直接使用int, double, String(要使用單引號包含), boolean
     */
    @Test
    public void testLiteralString() {
        assertTrue(parser.parseExpression("3.14159").getValue().equals(3.14159));
        assertTrue(parser.parseExpression("9.87E4").getValue(Integer.class).equals(98700));
        assertTrue(parser.parseExpression("false").getValue().equals(false));
        assertTrue(parser.parseExpression("'Hello'").getValue().equals("Hello"));
        assertTrue(parser.parseExpression("'''Hello'").getValue().equals("'Hello"));
        assertTrue(parser.parseExpression("'''Hello'''").getValue().equals("'Hello'"));
    }

    @Test
    public void testCalculation() {
        // 算術運算
        assertTrue(parser.parseExpression("(1 + 2) * 5").getValue().equals(15));
        assertTrue(parser.parseExpression("10 % 3").getValue().equals(1));
        assertTrue(parser.parseExpression("10 / 2").getValue().equals(5));
        assertTrue(parser.parseExpression("2 ^ 5").getValue().equals(32));

        // 比較運算
        assertTrue(parser.parseExpression("10 >= 5").getValue().equals(true));
        assertTrue(parser.parseExpression("10 ge 5").getValue().equals(true));
        assertTrue(parser.parseExpression("10 <= 5").getValue().equals(false));
        assertTrue(parser.parseExpression("10 le 5").getValue().equals(false));

        assertTrue(parser.parseExpression("10 > 5").getValue().equals(true));
        assertTrue(parser.parseExpression("10 gt 5").getValue().equals(true));
        assertTrue(parser.parseExpression("10 < 5").getValue().equals(false));
        assertTrue(parser.parseExpression("10 lt 5").getValue().equals(false));

        assertTrue(parser.parseExpression("0 == 0").getValue().equals(true));
        assertTrue(parser.parseExpression("0 eq 0").getValue().equals(true));
        assertTrue(parser.parseExpression("1 != 2").getValue().equals(true));

        // 邏輯運算
        assertTrue(parser.parseExpression("true && false").getValue().equals(false));
        assertTrue(parser.parseExpression("true || false").getValue().equals(true));
        assertTrue(parser.parseExpression("!false").getValue().equals(true));
        assertTrue(parser.parseExpression("true and false").getValue().equals(false));
        assertTrue(parser.parseExpression("true or false").getValue().equals(true));
        assertTrue(parser.parseExpression("not false").getValue().equals(true));
    }

    @Test
    public void testInvokingMethod() {
        assertTrue(parser.parseExpression("'hellow'.length()").getValue().equals(6));
    }

    @Test
    public void testAssessMethod() {
        // 將待評估的物件塞入，會利用reflection的方式將該物件的所有屬性和方法進行解析
        EvaluationContext context = new StandardEvaluationContext(product); // set root object

        // 如果get方法存在，優先調用get方法；如果不存在，嘗試存取name屬性
        assertEquals(parser.parseExpression("name").getValue(context, String.class), "開發產品");
        assertEquals(parser.parseExpression("getName()").getValue(context, String.class), "開發產品");

        // 也可以直接將root object傳入解析
        assertEquals(parser.parseExpression("name").getValue(product, String.class), "開發產品");
        assertEquals(parser.parseExpression("getName()").getValue(product, String.class), "開發產品");
    }

    @Test
    public void testAssessingCollection() {
        Object obj = new Object() {
            public List<String> getInterests() {
                return Arrays.asList("swimming", "hiking", "surfing the internet");
            }
        };

        assertEquals(parser.parseExpression("interests[0]").getValue(obj, String.class), "swimming");
        assertEquals(parser.parseExpression("interests[1]").getValue(obj, String.class), "hiking");
        assertEquals(parser.parseExpression("interests[2]").getValue(obj, String.class), "surfing the internet");
    }

    @Test
    public void testAssessingArray() {
        Object obj = new Object() {
            public String[] getInterests() {
                return new String[]{"swimming", "hiking", "surfing the internet"};
            }
        };

        assertEquals(parser.parseExpression("interests[0]").getValue(obj, String.class), "swimming");
        assertEquals(parser.parseExpression("interests[1]").getValue(obj, String.class), "hiking");
        assertEquals(parser.parseExpression("interests[2]").getValue(obj, String.class), "surfing the internet");
    }

    @Test
    public void testAssessingMap() {
        Object obj = new Object() {
            public Map<String, String> getInterests() {
                Map<String, String> map = new HashMap<>();
                map.put("most_favorite", "surfing the internet");
                map.put("second_favorite", "listening to music");
                map.put("third_favorite", "swimming");
                return map;
            }
        };

        assertEquals(parser.parseExpression("interests[most_favorite]").getValue(obj, String.class), "surfing the internet");
        assertEquals(parser.parseExpression("interests[second_favorite]").getValue(obj, String.class), "listening to music");
        assertEquals(parser.parseExpression("interests[third_favorite]").getValue(obj, String.class), "swimming");
    }

//    @Test
//    public void test1() {
//        String greetingExp = "Hello, #{ #user }";
//
//        EvaluationContext context = new StandardEvaluationContext();
//        context.setVariable("user", "Gangyou");
//
//        Expression expression = parser.parseExpression(greetingExp, new TemplateParserContext());
//        System.out.println(expression.getValue(context, String.class));
//    }

    @Test
    public void testCreatingListInstance() {
        // create an empty list
        List<String> list = parser.parseExpression("{}").getValue(ArrayList.class);
        System.out.println(String.format("list = %s, class = %s", list.toString(), list.getClass().getName()));

        // create an integer list
        List<Integer> list_i = parser.parseExpression("{1,2,3,4,5,6,7,8,9,10}").getValue(ArrayList.class);
        System.out.println(String.format("list = %s, class = %s", list_i.toString(), list.getClass().getName()));

        // create an string list
        List<String> list_s = parser.parseExpression("{{'1','2','3'},{'4','5'},{'6','7','8','9','10'}}").getValue(ArrayList.class);
        System.out.println(String.format("list = %s, class = %s", list_s.toString(), list.getClass().getName()));
    }

    @Test
    public void testCreatingMapInstance() {
        Map<String, String> emptyMap = parser.parseExpression("{:}").getValue(HashMap.class);
        System.out.println(emptyMap);
        Map<String, Long> map = parser.parseExpression("{'first':1L, 'second':2L, 'third':3L}").getValue(HashMap.class);
        System.out.println(map);
    }

    @Test
    public void testCreatingArrayInstance() {
        int[] numbers = parser.parseExpression("new int[]{1,2,3}").getValue(int[].class);
        Arrays.stream(numbers).forEach(num -> System.out.println(num));

        String[] strs = parser.parseExpression("new String[10]").getValue(String[].class);
        System.out.println("length = " + strs.length);

        // 不支持二維陣列
        try {
            int[] multipleArray = parser.parseExpression("new int[2][5]{6,7,8}{1,2,3,4,5}").getValue(int[].class);
            Arrays.stream(multipleArray).forEach(num -> System.out.println(num));
        } catch (EvaluationException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 集合投影"collection.![projectionExpression]"
     * 將列表每個元素取出並呼叫projectionExpression所定義的方法並將結果蒐集成新列表
     */
    @Test
    public void getNewListByFiltering() {
        Object obj = new Object() {
            public List<String> getNames() {
                return Arrays.asList("Andy", "Anna", "Alex", "Betty", "Billy", "Ben", "Cindy", "Christine", "David");
            }
        };

        // 將列表裡面的所有元素呼叫contains的結果後回傳新的列表

        List<String> names = parser.parseExpression("names.![contains('e')]").getValue(obj, ArrayList.class);
        System.out.println(names);
    }

    @Test
    public void getNewMapByFiltering() {
        Object obj = new Object() {
            public Map<String, String> getMap() {
                Map<String, String> map = new HashMap<>();
                map.put("first", "1");
                map.put("second", "2");
                map.put("third", "3");
                return map;
            }
        };

        // 呼叫entry.value

        List<String> values = parser.parseExpression("map.![value]").getValue(obj, ArrayList.class);
        System.out.println(values);
    }

    @Test
    public void testRootObject() {
        EvaluationContext context = new StandardEvaluationContext(product);

        // #root在表達式中永遠指向rootObject
        Expression expression_1 = parser.parseExpression("name");
        Expression expression_2 = parser.parseExpression("#root.name");

        // 使用EvaluationContext解析表達式的值
        System.out.println("name = " + expression_1.getValue(context, String.class));
        System.out.println("name = " + expression_2.getValue(context, String.class));
    }

    @Test
    public void testExpressionThis() {
        String e1 = "{1,2,3,4,5,6,7,8,9,10}";
        String odd = "#root.?[(#this % 2 == 1)]";
        String even = "#root.?[#this % 2 == 0]";

        // #root在表達式中永遠指向rootObject
        List<Integer> numbers = parser.parseExpression(e1).getValue(ArrayList.class);
        Expression expression_odd = parser.parseExpression(odd);
        Expression expression_even = parser.parseExpression(even);

        EvaluationContext context = new StandardEvaluationContext(numbers);

        List<Integer> odd_numbers = expression_odd.getValue(context, ArrayList.class);
        List<Integer> even_numbers = expression_even.getValue(context, ArrayList.class);


        // 使用EvaluationContext解析表達式的值
        System.out.println("odd_numbers = " + odd_numbers);
        System.out.println("even_numbers = " + even_numbers);
    }

    public static int plusOne(int num) {
        return num + 1;
    }

    /**
     * StandardEvaluationContext允許透貴registerFunction(funName,method)方法註冊自定義方法，
     * 該方法比須為static，方法參數傳入方法名和java.reflect.Method物件，
     * 經過註冊之後便可以在表達式中使用#funName()調用該方法
     */
    @Test
    public void registerMethod() {
        Method plusOne = null;
        try {
            plusOne = SpelExpressionTest.class.getDeclaredMethod("plusOne", int.class);
        } catch (NoSuchMethodException e) {
        }

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.registerFunction("plusOne", plusOne);
        int result = parser.parseExpression("#plusOne(10)").getValue(context, int.class);
        System.out.println("result = " + result);
    }


    @Test
    public void testCreatingInstanceAndCallingMethodsOfIt() {
        // 只有java.lang package底下的類別不需要加package路徑
        Expression expression = parser.parseExpression("new java.util.Date().toLocaleString()");
        String currentTime = expression.getValue(String.class);
        System.out.println(currentTime);
    }

    // set variable

    @Test
    public void testSettingVariable() {
        Date date = new Date();
        parser.parseExpression("date").setValue(date, 1);
        int d = parser.parseExpression("date").getValue(date, int.class);
        System.out.println(d);
        System.out.println(parser.parseExpression("getTime()").getValue(date, long.class));
    }

    @Test
    public void testSettingVariable_1() {
        // 不只可以設定rootObject，也可以設定變數、方法，設置變數使用setVariable()方法，然後再表達式通過#varName取出
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("product", product);

        Expression expression = parser.parseExpression("#product.name");
        // 使用EvaluationContext解析表達式的值
        String name = expression.getValue(context, String.class);
        System.out.println("name = " + name);
    }

    // ??????

    @Test
    public void testSettingVariable_2() {
        List<String> animals = new ArrayList<>();
        animals.add("Zebra");
        animals.add("Spider");
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("animals", animals);
        parser.parseExpression("#animals[0]").setValue(context, "Panda~");

        String firstAnimal = parser.parseExpression("#animals[0]").getValue(context, String.class);
        System.out.println(firstAnimal);
    }

    @Test
    public void testSettingVariable_3() {
        Map<String, String> map = new HashMap<>();

        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("map", map);

        // 對於map的賦值而言是通過按key進行的，對應的key在map中可以先不存在
        parser.parseExpression("#map['key1']").setValue(context, 1);
        int first = (Integer) parser.parseExpression("#map['key1']").getValue(context);

        System.out.println(first);
    }

    /**
     * 訪問靜態方法和屬性
     * T(type) type -> class全名含package
     */
    @Test
    public void testInvokingStaticMethod() {
        LocalDate now = parser.parseExpression("T(java.time.LocalDate).now()").getValue(LocalDate.class);
        System.out.println(now);
    }

    @Test
    public void testRegularExpression() {

//        Boolean result = parser.parseExpression("123 matches '\\d{3}'").getValue(Boolean.class);
//        System.out.println("result = " + result);
        String name = parser.parseExpression("department.name").getValue(String.class);
        System.out.println(name);

    }

    @Test
    public void testInstanceOf() {
        Boolean result = parser.parseExpression("'test' instanceOf T(String)").getValue(Boolean.class);
        System.out.println("test instanceOf String --> " + result);
    }

    @Test
    public void testTernaryOperator() {
        String result = parser.parseExpression("1 > 2 ? '真的啦!' : '唬爛!'").getValue(String.class);
        System.out.println(result);
        result = parser.parseExpression("10 > 5 ? '真的啦!' : '唬爛!'").getValue(String.class);
        System.out.println(result);
    }

    /**
     * SpEL支持模板功能，可以在表達式裡面在封裝一層Spel(將表達式當作一層樣板)
     * 如此一來可以將普通字串和表達式一起解析(只會將#{exp}的地方進行運算並替換)
     * 透過TemplateParserContext，實現樣板的功能
     */
    @Test
    public void testTemplateContext() {
        String expressionStr_1 = "Now is #{T(java.time.LocalDateTime).now().toString()}";
        String expressionStr_2 = "{1,2,3,4,5}";
        ParserContext context = new TemplateParserContext();

        System.out.println(parser.parseExpression(expressionStr_1, context).getValue(String.class));
        // 原本應該是list的建構表達式，但是在模板下被解析成字串
        System.out.println(parser.parseExpression(expressionStr_2, context).getValue(String.class));
    }

    /**
     * a?:b -> 設定預設值
     * if a == null, return b
     * if a != null, return a
     */
    @Test
    public void testDefaultValue() {
       System.out.println(parser.parseExpression("'abc'?:'cba'").getValue(String.class));
       System.out.println(parser.parseExpression("#product?: 'nothing here'").getValue(String.class));

    }

    /**
     * 安全導航，如果連續調用中出現null，則直接回傳null
     * (避免出現nullPointException)
     */
    @Test
    public void testSafeInvoking() {
        Object obj = new Object() {
            public String hello() {
                return null;
            }
        };
        String result = parser.parseExpression("#root.hello()?.toString()").getValue(obj, String.class);
        System.out.println(result);

        result = parser.parseExpression("T(System)?.getProperty('meow')?.length()").getValue(String.class);
        System.out.println(result);
    }

    /**
     * 訪問Bean對象，透過實作BeanResolver resolve(EvaluationContext context, String beanName)
     * 透過beanName解析回傳一個bean物件，並註冊到StandardEvaluationContext
     * 便可以在表達式中使用@beanName訪問該bean物件
     */
    @Test
    public void testBeanResolver() {
        BeanResolver br = (EvaluationContext context, String beanName) -> {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("bean/scope/application.xml");
            return ctx.getBean(beanName);
        };

        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setBeanResolver(br);
        String name = parser.parseExpression("@iphoneXs.brand").getValue(ctx, String.class);
        System.out.println(name);
    }

    /**
     * 使用SpelParserConfiguration配置SpelExpressionParser
     */
    @Test
    public void testSpelParserConfiguration() {
         class Human {
            public List<String> interests;
        }

        Human human = new Human();
         // 如果遇到null的變數，則自動new一個instance，如果遇到超出Collection的訪問，則自動擴展
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);
        parser = new SpelExpressionParser(config);
        System.out.println(parser.parseExpression("interests").getValue(human));
        System.out.println(parser.parseExpression("interests[5]").getValue(human));
        System.out.println(parser.parseExpression("interests.size()").getValue(human));
    }

    @Test
    public void readProperty() {
        class Human {
            @Value("#{systemProperties['user.dir']}")
            public String dir;
        }

        String dir = parser.parseExpression("dir").getValue(new Human(), String.class);
        System.out.println(dir);
    }

}
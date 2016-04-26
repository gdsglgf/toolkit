import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SumTest {
    public static void main(String[] args) {
        String command = "sum";
        try {
            Process p = Runtime.getRuntime().exec(command);

            // 输入测试数据
            PrintWriter stdin = new PrintWriter(p.getOutputStream(), true);
            int length = Integer.parseInt(args[0]);
            System.out.println(length);
            for (int i = 1; i <= length; i++) {
                stdin.write(String.format("%d ", i));
                System.out.println("stdin " + i);
                stdin.flush();
            }
            stdin.close();

            p.waitFor();

            // 获取运行结果
            BufferedReader stdout = new BufferedReader((new InputStreamReader(
                    p.getInputStream())));
            String line = null;
            while ((line = stdout.readLine()) != null) {
                System.out.println("result-->" + line);
            }
            stdout.close();
        } catch (Exception e) {
            
        }
    }
}
import com.maple.bean.ExpBean;
import com.maple.bean.JobBean;
import com.maple.bean.JobEnum;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Description :
 * @create : 2023-2-1 11:12
 * @Author : lys
 * @Modified By : lys
 */
public class MainController implements Initializable {

    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, Executors.defaultThreadFactory());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static HashMap<JobEnum, JobBean> jobMap = new HashMap<>();

    static {
        jobMap.put(JobEnum.HERO, new JobBean(JobEnum.HERO, "asset/hero.jpg", "力量+40/80/100", "HP低于maxHP 15%时3秒内 每秒恢复23%/29%35%HP冷却370/290/210秒"));
    }


    public void initialize() {
        System.out.println("MainController initialize");
    }

    @FXML
    private Button btnStartDDSE;

    @FXML
    private Button btnPwd;

    @FXML
    private Button btnClose;

    @FXML
    private TextArea textAreaInfo;

    @FXML
    private Text dailyText;


    @FXML
    private TableView<ExpBean> tableView;
    @FXML
    private TableColumn<ExpBean, String> name;
    @FXML
    private TableColumn<ExpBean, String> desc;
    @FXML
    private TableColumn<ExpBean, String> fun;


    // job相关
    @FXML
    ChoiceBox<JobEnum> choiceBox;
    @FXML
    ImageView choiceImg;
    @FXML
    TextField jobRole;
    @FXML
    TextField jobLink;

    //  记事本
    @FXML
    TextArea nodeTextArea;


    /**
     * 点击保存保存到本地文件
     * @param event
     */
    @FXML
    protected void handleBtnSaveAction(ActionEvent event) throws IOException {
        String noteContent = nodeTextArea.getText();

        writeFile(noteContent);

    }

    private void writeFile(String noteContent) throws IOException {
        File file = new File("./note.txt");
        System.out.println("file path: " + file.getAbsolutePath());

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        // 每次都重新写
        bufferedWriter.write(noteContent);
        bufferedWriter.flush();
        bufferedWriter.close();
    }


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *  @param location
     * The location used to resolve relative paths for the root object, or
     * <tt>null</tt> if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTodayTime();
        initExpTab();
        initChoice();
        initNoteInfo();

    }

    /**
     * 初始化记事本信息
     */
    private void initNoteInfo() {
        File file = new File("./note.txt");
        System.out.println("file path: " + file.getAbsolutePath());

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String noteStr = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((noteStr = bufferedReader.readLine()) != null) {
                stringBuilder.append(noteStr);
            }
            nodeTextArea.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initChoice() {

        ArrayList<JobEnum> choiceBoxList = new ArrayList<>();
        choiceBoxList.add(JobEnum.HERO);


        ObservableList<JobEnum> availableChoices = FXCollections.observableArrayList(choiceBoxList);
        choiceBox.setItems(availableChoices);

        // add a listener
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // 是下标
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                JobEnum jobEnum = choiceBoxList.get(newValue.intValue());
                JobBean jobBean = jobMap.get(jobEnum);
                Image image = new Image(jobBean.getPerfectUrl());
                choiceImg.setImage(image);

                jobRole.setText(jobBean.getJobRole());
                jobLink.setText(jobBean.getJobLink());

            }
        });
    }

    private void initExpTab() {
        name.setCellValueFactory(new PropertyValueFactory<ExpBean, String>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<ExpBean, String>("desc"));
        fun.setCellValueFactory(new PropertyValueFactory<ExpBean, String>("fun"));

        tableView.getItems().setAll(parseUserList());
    }

    private List<ExpBean> parseUserList() {
        List<ExpBean> expBeans = new ArrayList<>();
        expBeans.add(new ExpBean("轮回石碑", "酷东西", "增加地图最大怪物和刷怪量"));
        return expBeans;
    }

    private void showTodayTime() {
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                LocalDateTime now = LocalDateTime.now();
                String nowTime = formatter.format(now);

                LocalDateTime maxDateTime = LocalDateTime.of(now.toLocalDate(), LocalTime.MAX);

                long seconds = Duration.between(now, maxDateTime).getSeconds();
                //获取秒数
                long s = seconds % 60;
                //获取分钟数
                long m = seconds / 60 % 60;
                //获取小时数
                long h = seconds / 60 / 60 % 24;
                //获取天数
                long d = seconds / 60 / 60 / 24;

                String between = d + "天" + h + "时" + m + "分" + s + "秒";
                String showInfoFormat = "当前时间：%s,  距离今天结束还有：%s。 合理安排每日时间";
                dailyText.setText(String.format(showInfoFormat, nowTime, between));
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}

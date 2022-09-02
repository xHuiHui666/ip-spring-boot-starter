package cn.wolf.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("ipProperties")
@ConfigurationProperties(prefix = "tools.ip")
public class IpProperties {

    /**
     *  日志显示周期
     */
    private long cycle = 5l;

    /**
     * 是否周期内重置数据
     */
    private Boolean cycleReset = false;

    /**
     * 日志输出模式   detail: 详细模式   simple: 极简模式
     * 默认详细模式
     */

    private String model = LogModel.DETAIL.value;

    public enum LogModel{
        DETAIL("DETAIL"),
        SIMPLE("SIMPLE");
        private String value;

        LogModel(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public long getCycle() {
        return cycle;
    }

    public void setCycle(long cycle) {
        this.cycle = cycle;
    }

    public Boolean getCycleReset() {
        return cycleReset;
    }

    public void setCycleReset(Boolean cycleReset) {
        this.cycleReset = cycleReset;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

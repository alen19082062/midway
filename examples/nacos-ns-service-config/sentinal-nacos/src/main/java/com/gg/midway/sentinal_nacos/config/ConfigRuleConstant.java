package com.gg.midway.sentinal_nacos.config;

public class ConfigRuleConstant {
    // public final static String remoteAddress = "localhost";
    public final static String remoteAddress = "119.29.84.103";

    public final static String groupId = "DEFAULT_GROUP";
    public final static String dataId = "flow-rule";

    public final static String rule = "[\n"
            + "  {\n"
            + "    \"resource\": \"hello\",\n"
            + "    \"controlBehavior\": 0,\n"
            + "    \"count\": 1.0,\n"
            + "    \"grade\": 1,\n"
            + "    \"limitApp\": \"default\",\n"
            + "    \"strategy\": 0\n"
            + "  },\n"
            + "  {\n"
            + "    \"resource\": \"conf\",\n"
            + "    \"controlBehavior\": 0,\n"
            + "    \"count\": 1.0,\n"
            + "    \"grade\": 1,\n"
            + "    \"limitApp\": \"default\",\n"
            + "    \"strategy\": 0\n"
            + "  }\n"
            + "]";
}

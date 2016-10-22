package com.stu.fastpan.dao.mybatis;

public class LsurlDB_Shortlink extends AbstractGen
{

    @Override
    public String getConfPath()
    {
        return "src/main/resources/config/generatorConfig.xml";
    }
    
    public static void main(String[] args)
    {
        AbstractGen gen = new LsurlDB_Shortlink();
        gen.gen();
    }
}

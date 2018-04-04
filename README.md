# mybatis-generator-tool
Since the code comments generated by mybatis-generator-core are not suitable for display in the development tool,so i decide to modify the mybatis-generator-core and add the class IdePrettyCommentGenerator to generate the API document that is suitable for the development tool display.
* You can use the following way,the key is to modify the commentGenerator node's type attribute value to org.mybatis.generator.internal.IdePrettyCommentGenerator.


    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE generatorConfiguration
            PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
            "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
    
    <generatorConfiguration>
       <context id="userinfo" targetRuntime="MyBatis3DynamicSql">
           <property name="autoDelimitKeywords" value="true"/>
           <property name="beginningDelimiter" value="`"/>
           <property name="endingDelimiter" value="`"/>
           <property name="javaFileEncoding" value="UTF-8"/>
    
           <commentGenerator type="org.mybatis.generator.internal.IdePrettyCommentGenerator">
               <property name="suppressDate" value="false" />
               <property name="suppressAllComments" value="false" />
               <property name="addRemarkComments" value="true" />
               <property name="dateFormat" value="yyyy-MM-dd HH:mm:ss" />
           </commentGenerator>
    
           <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                           connectionURL="jdbc:mysql://host:port/dbName?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false"
                           userId="dbLoginName"
                           password="dbPassword">
           </jdbcConnection>
    
           <javaModelGenerator targetPackage="package.model.generate"
                               targetProject="src/main/java">
               <property name="enableSubPackages" value="true" />
               <property name="trimStrings" value="true" />
           </javaModelGenerator>
    
           <sqlMapGenerator targetPackage="mapper/generate" targetProject="src/main/resources">
               <property name="enableSubPackages" value="false"/>
           </sqlMapGenerator>
    
           <javaClientGenerator type="XMLMAPPER" targetPackage="package.mapper.generate" targetProject="src/main/java">
               <property name="enableSubPackages" value="ture"/>
           </javaClientGenerator>
    
           <table tableName="userinfo" domainObjectName="UserInfo" >
           </table>
    
       </context>
    </generatorConfiguration>

* you can also use the following maven repository,instead of compiling source code


    <repositories>
		<repository>
			<id>bruce-lwl</id>
			<url>https://raw.github.com/brucelwl/maven-repo/master/</url>
			<snapshots>
				<enabled>true</enabled>
				<updatePolicy>always</updatePolicy>
			</snapshots>
		</repository>
	</repositories>
	</dependencies>
	       <dependency>
    		<groupId>com.lwl</groupId>
    		<artifactId>mybatis-generator-tool</artifactId>
    		<version>1.3.6</version>
    		<scope>test</scope>
    	</dependency>
    </dependencies>
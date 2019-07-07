<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:exsl="http://exslt.org/common"
xmlns:math="http://exslt.org/math"
xmlns:xs="http://www.w3.org/2001/XMLSchema"
extension-element-prefixes="exsl math">
<xsl:output method="text" encoding="UTF-8"/>

<xsl:template match="/">
  
 <xsl:choose>
    <xsl:when test="HomeLoan/Category = 'Self Employed'">
         <xsl:choose>
         <xsl:when test=" (HomeLoan/Age &gt; 17) and (HomeLoan/Age &lt; 71)">
          <xsl:text>Your yearly income is </xsl:text>
          <xsl:value-of select="HomeLoan/MonthlyIncome*12"></xsl:value-of>
          <xsl:text>  Bank assume that 40% of your yearly income you will be using for repaying the Loan ammount</xsl:text>

<!--           <xsl:with-param name="amount_after_deduction" select="HomeLoan/MonthlyIcome - 0.4 * HomeLoan/MonthlyIncome*12 "/>
 -->
           <xsl:text>  Maximum amount of Home Loan You can get is </xsl:text>
        <xsl:value-of select="(HomeLoan/MonthlyIncome - (0.6 * HomeLoan/MonthlyIncome ))* 4  * HomeLoan/TimePeriod"/>

           <xsl:text>. And bank with minimum interest rate  </xsl:text>
             <xsl:value-of select="HomeLoan/rateofInterest"/>
               <xsl:text> on Home Loan is  </xsl:text>

<!--              <xsl:for-each select="HomeLoan/Banks">
 -->                <xsl:value-of select="HomeLoan/BankName"/>
   <!--           </xsl:for-each> -->

         </xsl:when>

         <xsl:otherwise>
            <xsl:text>Your age should be in between 18 and 70 years (Inclusive).</xsl:text>
         </xsl:otherwise>
       </xsl:choose>
     
    </xsl:when>


  <xsl:otherwise>
  <xsl:choose>
         <xsl:when test=" (HomeLoan/Age &gt; 17) and (HomeLoan/Age &lt; 71)">
           <xsl:text>Maximum amount of Home Loan You can get is </xsl:text>
      <xsl:text>Your yearly income is </xsl:text>
          <xsl:value-of select="HomeLoan/MonthlyIncome*12"></xsl:value-of>
          <xsl:text>  Bank assume that 40% of your yearly income you will be using for repaying the Loan ammount </xsl:text>

<!--           <xsl:with-param name="amount_after_deduction" select="HomeLoan/MonthlyIcome - 0.4 * HomeLoan/MonthlyIncome*12 "/>
 -->
           <xsl:text>  Maximum amount of Home Loan You can get is </xsl:text>
        <xsl:value-of select="(HomeLoan/MonthlyIncome - (0.6 * HomeLoan/MonthlyIncome))* 6 * HomeLoan/TimePeriod"/>

           <xsl:text> and bank with minimum interest rate on Home Loan  </xsl:text>
             <xsl:value-of select="HomeLoan/rateofInterest"/>
               <xsl:text> is  </xsl:text>

                <xsl:value-of select="HomeLoan/BankName"/>
       
         </xsl:when>

         <xsl:otherwise>
            <xsl:text>Your age should be in between 18 and 70 years (Inclusive).</xsl:text>
         </xsl:otherwise>
       </xsl:choose>
   </xsl:otherwise>
</xsl:choose>

</xsl:template>

</xsl:stylesheet>



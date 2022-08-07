package com.brave;


import cn.hutool.crypto.SmUtil;

/**
 * @author: Qiang.cao
 * Date: 2022/6/13
 * Time: 20:50
 * Description:
 */
public class TestService {

    public static void main(String[] args){
        //String username = "tjopen";
        //String password = "1A*pp@0A";
        //
        //String token = "Basic " +  Base64Util.encode(username + ":" + password);
        //
        //System.out.println(token);

        //System.out.println(String.format("%s%s", "first", "ConnectionFactory"));

        //System.out.println(String.valueOf(System.currentTimeMillis()));
        //
        //HttpRequest httpRequest = HttpRequest.get("http://testmno.enovatemotors.com/mno-general-open-api/v2/vehicles/detail_info")
        //        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTc4NzgxNDgsInVzZXJuYW1lIjoieGF3X2FwaTA3MDQifQ.g_trecTcV9eJevtH6PbbQ8CeUr3ea6vBNMx_3FWZ_oQ")
        //        .form("keyCode", "LGBM4AE45KS456894").form("keyType", "VIN");
        //System.out.println(httpRequest);
        //HttpResponse execute = httpRequest.execute();
        //System.out.println(execute);

        //Digester digester = DigestUtil.digester("sm3");
        //String digestHex = digester.digestHex("BsWsa44fZ3LH5i11KjjO3U16n1w==");
        //System.out.println(digestHex);

        String s = SmUtil.sm3("BsWsa44fZ3LH5i11KjjO3U16n1w==");
        System.out.println(s);


    }

}

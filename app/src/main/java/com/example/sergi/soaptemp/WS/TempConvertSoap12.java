package com.example.sergi.soaptemp.WS;




//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 4.1.9.1
//
// Created by Quasar Development at 31-12-2015
//
//---------------------------------------------------




import org.ksoap2.HeaderProperty;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.*;

import java.util.List;


public class TempConvertSoap12
{
    interface FERIWcfMethod
    {
        ExtendedSoapSerializationEnvelope CreateSoapEnvelope() throws java.lang.Exception;

        java.lang.Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope,java.lang.Object result) throws java.lang.Exception;
    }

    String url="http://www.w3schools.com/xml/tempconvert.asmx";

    int timeOut=60000;
    public List< HeaderProperty> httpHeaders;
    public boolean enableLogging;

    IServiceEvents callback;
    public TempConvertSoap12(){}

    public TempConvertSoap12(IServiceEvents callback)
    {
        this.callback = callback;
    }
    public TempConvertSoap12(IServiceEvents callback, String url)
    {
        this.callback = callback;
        this.url = url;
    }

    public TempConvertSoap12(IServiceEvents callback, String url, int timeOut)
    {
        this.callback = callback;
        this.url = url;
        this.timeOut=timeOut;
    }

    protected org.ksoap2.transport.Transport createTransport()
    {
        try
        {
            java.net.URI uri = new java.net.URI(url);
            if(uri.getScheme().equalsIgnoreCase("https"))
            {
                int port=uri.getPort()>0?uri.getPort():443;
                return new HttpsTransportSE(uri.getHost(), port, uri.getPath(), timeOut);
            }
            else
            {
                return new HttpTransportSE(url,timeOut);
            }

        }
        catch (java.net.URISyntaxException e)
        {
        }
        return null;
    }
    
    protected ExtendedSoapSerializationEnvelope createEnvelope()
    {
        ExtendedSoapSerializationEnvelope envelope= new ExtendedSoapSerializationEnvelope(ExtendedSoapSerializationEnvelope.VER12);
        return envelope;
    }
    
    protected java.util.List sendRequest(String methodName,ExtendedSoapSerializationEnvelope envelope,org.ksoap2.transport.Transport transport  )throws java.lang.Exception
    {
        return transport.call(methodName, envelope, httpHeaders);
    }

    java.lang.Object getResult(java.lang.Class destObj,java.lang.Object source,String resultName,ExtendedSoapSerializationEnvelope __envelope) throws java.lang.Exception
    {
        if(source==null)
        {
            return null;
        }
        if(source instanceof SoapPrimitive)
        {
            SoapPrimitive soap =(SoapPrimitive)source;
            if(soap.getName().equals(resultName))
            {
                java.lang.Object instance=__envelope.get(source,destObj);
                return instance;
            }
        }
        else
        {
            SoapObject soap = (SoapObject)source;
            if (soap.hasProperty(resultName))
            {
                java.lang.Object j=soap.getProperty(resultName);
                if(j==null)
                {
                    return null;
                }
                java.lang.Object instance=__envelope.get(j,destObj);
                return instance;
            }
            else if( soap.getName().equals(resultName)) {
                java.lang.Object instance=__envelope.get(source,destObj);
                return instance;
            }
       }

       return null;
    }

        
    public String FahrenheitToCelsius(final String Fahrenheit ) throws java.lang.Exception
    {
        return (String)execute(new FERIWcfMethod()
        {
            @Override
            public ExtendedSoapSerializationEnvelope CreateSoapEnvelope(){
              ExtendedSoapSerializationEnvelope __envelope = createEnvelope();
                SoapObject __soapReq = new SoapObject("http://www.w3schools.com/xml/", "FahrenheitToCelsius");
                __envelope.setOutputSoapObject(__soapReq);
                
                PropertyInfo __info=null;
                __info = new PropertyInfo();
                __info.namespace="http://www.w3schools.com/xml/";
                __info.name="Fahrenheit";
                __info.type=PropertyInfo.STRING_CLASS;
                __info.setValue(Fahrenheit!=null?Fahrenheit:SoapPrimitive.NullSkip);
                __soapReq.addProperty(__info);
                return __envelope;
            }
            
            @Override
            public java.lang.Object ProcessResult(ExtendedSoapSerializationEnvelope __envelope,java.lang.Object __result)throws java.lang.Exception {
                SoapObject __soap=(SoapObject)__result;
                java.lang.Object obj = __soap.getProperty("FahrenheitToCelsiusResult");
                if (obj != null && obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    return j.toString();
                }
                else if (obj!= null && obj instanceof String){
                    return (String)obj;
                }
                return null;
            }
        },"http://www.w3schools.com/xml/FahrenheitToCelsius");
    }
    
    public android.os.AsyncTask< Void, Void, OperationResult< String>> FahrenheitToCelsiusAsync(final String Fahrenheit)
    {
        return executeAsync(new Functions.IFunc< String>() {
            public String Func() throws java.lang.Exception {
                return FahrenheitToCelsius( Fahrenheit);
            }
        });
    }
    
    public String CelsiusToFahrenheit(final String Celsius ) throws java.lang.Exception
    {
/*This feature is available in Premium account, Check http://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account*/
        return null;    
    }
    
    public android.os.AsyncTask< Void, Void, OperationResult< String>> CelsiusToFahrenheitAsync(final String Celsius)
    {
        return executeAsync(new Functions.IFunc< String>() {
            public String Func() throws java.lang.Exception {
                return CelsiusToFahrenheit( Celsius);
            }
        });
    }

    
    protected java.lang.Object execute(FERIWcfMethod wcfMethod,String methodName) throws java.lang.Exception
    {
        org.ksoap2.transport.Transport __httpTransport=createTransport();
        __httpTransport.debug=enableLogging;
        ExtendedSoapSerializationEnvelope __envelope=wcfMethod.CreateSoapEnvelope();
        try
        {
            sendRequest(methodName, __envelope, __httpTransport);
            
        }
        finally {
            if (__httpTransport.debug) {
                if (__httpTransport.requestDump != null) {
                    android.util.Log.i("requestDump",__httpTransport.requestDump);
                }
                if (__httpTransport.responseDump != null) {
                    android.util.Log.i("responseDump",__httpTransport.responseDump);
                }
            }
        }
        java.lang.Object __retObj = __envelope.bodyIn;
        if (__retObj instanceof org.ksoap2.SoapFault){
            org.ksoap2.SoapFault __fault = (org.ksoap2.SoapFault)__retObj;
            throw convertToException(__fault,__envelope);
        }else{
            return wcfMethod.ProcessResult(__envelope,__retObj);
        }
    }
    
    protected < T> android.os.AsyncTask< Void, Void, OperationResult< T>>  executeAsync(final Functions.IFunc< T> func)
    {
        return new android.os.AsyncTask< Void, Void, OperationResult< T>>()
        {
            @Override
            protected void onPreExecute() {
                callback.Starting();
            };
            @Override
            protected OperationResult< T> doInBackground(Void... params) {
                OperationResult< T> result = new OperationResult< T>();
                try
                {
                    result.Result= func.Func();
                }
                catch(java.lang.Exception ex)
                {
                    ex.printStackTrace();
                    result.Exception=ex;
                }
                return result;
            }
            
            @Override
            protected void onPostExecute(OperationResult< T> result)
            {
                callback.Completed(result);
            }
        }.execute();
    }
        
    java.lang.Exception convertToException(org.ksoap2.SoapFault fault,ExtendedSoapSerializationEnvelope envelope)
    {

        return new java.lang.Exception(fault.faultstring);
    }
}


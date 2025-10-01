package com.stripeterminal.readers;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.stripe.stripeterminal.Terminal;
import com.stripe.stripeterminal.callable.Callback;
import com.stripe.stripeterminal.callable.Cancelable;
import com.stripe.stripeterminal.callable.ReaderCallback;
import com.stripe.stripeterminal.callable.TerminalListener;
import com.stripe.stripeterminal.log.LogLevel;
import com.stripe.stripeterminal.model.external.ConnectionConfiguration;
import com.stripe.stripeterminal.model.external.DiscoveryConfiguration;
import com.stripe.stripeterminal.model.external.DiscoveryMethod;
import com.stripe.stripeterminal.model.external.Reader;
import com.stripe.stripeterminal.model.external.TerminalException;

import java.util.List;

@CapacitorPlugin(name = "StripeTerminal")
public class StripeTerminalPlugin extends Plugin {
    
    private Cancelable discoveryCancelable;
    private JSArray discoveredReaders = new JSArray();

    @PluginMethod
    public void initialize(PluginCall call) {
        // Esta línea obtiene la clave pública de Stripe desde los parámetros enviados por JavaScript
        // Es necesaria para autenticar con la API de Stripe Terminal
        String publishableKey = call.getString("publishableKey");
        
        if (publishableKey == null) {
            call.reject("publishableKey is required");
            return;
        }

        try {
            Terminal.initTerminal(
                getContext(),
                LogLevel.VERBOSE,
                new TokenProvider(publishableKey),
                new TerminalListener() {
                    @Override
                    public void onUnexpectedReaderDisconnect(Reader reader) {
                        // Handle unexpected disconnection
                    }
                }
            );
            call.resolve();
        } catch (Exception e) {
            call.reject("Failed to initialize Terminal: " + e.getMessage());
        }
    }

    @PluginMethod
    public void discoverReaders(PluginCall call) {
        discoveredReaders = new JSArray();
        
        DiscoveryConfiguration config = new DiscoveryConfiguration.TapToPayBuilder()
            .build();

        discoveryCancelable = Terminal.getInstance().discoverReaders(
            config,
            new ReaderCallback() {
                @Override
                public void onSuccess(List<Reader> readers) {
                    JSArray readersArray = new JSArray();
                    
                    for (Reader reader : readers) {
                        JSObject readerObj = new JSObject();
                        readerObj.put("id", reader.getId());
                        readerObj.put("label", reader.getLabel());
                        readerObj.put("deviceType", reader.getDeviceType().toString());
                        
                        if (reader.getBatteryLevel() != null) {
                            readerObj.put("batteryLevel", reader.getBatteryLevel());
                        }
                        
                        readersArray.put(readerObj);
                    }
                    
                    JSObject result = new JSObject();
                    result.put("readers", readersArray);
                    call.resolve(result);
                }

                @Override
                public void onFailure(TerminalException e) {
                    call.reject("Failed to discover readers: " + e.getErrorMessage());
                }
            },
            new Callback() {
                @Override
                public void onSuccess() {
                    // Discovery completed
                }

                @Override
                public void onFailure(TerminalException e) {
                    call.reject("Discovery failed: " + e.getErrorMessage());
                }
            }
        );
    }

    @PluginMethod
    public void cancelDiscoverReaders(PluginCall call) {
        if (discoveryCancelable != null) {
            discoveryCancelable.cancel(new Callback() {
                @Override
                public void onSuccess() {
                    call.resolve();
                }

                @Override
                public void onFailure(TerminalException e) {
                    call.reject("Failed to cancel discovery: " + e.getErrorMessage());
                }
            });
        } else {
            call.resolve();
        }
    }
}

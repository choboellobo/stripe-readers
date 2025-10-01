package com.stripeterminal.readers;

import com.stripe.stripeterminal.callable.ConnectionTokenCallback;
import com.stripe.stripeterminal.callable.ConnectionTokenProvider;
import com.stripe.stripeterminal.model.external.ConnectionTokenException;

public class TokenProvider implements ConnectionTokenProvider {
    private String publishableKey;

    public TokenProvider(String publishableKey) {
        this.publishableKey = publishableKey;
    }

    @Override
    public void fetchConnectionToken(ConnectionTokenCallback callback) {
        // En un entorno de producción, deberías obtener el token desde tu backend
        // Por ahora, esto es un placeholder
        try {
            // Aquí deberías hacer una llamada a tu backend para obtener el connection token
            // callback.onSuccess("token_obtenido_del_backend");
            callback.onFailure(new ConnectionTokenException("Token provider not implemented. You need to implement token fetching from your backend."));
        } catch (Exception e) {
            callback.onFailure(new ConnectionTokenException(e.getMessage()));
        }
    }
}

package br.com.fiap.gerenciadorDepedidos.entrega.services.apiMelhorEnvio;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiMelhorEnvioConsumer {

    public HttpResponse<String> consultarDadosMelhorEnvio(RequestBodyData requestBodyData) throws IOException, InterruptedException {

        Gson gson = new Gson();
        String json = gson.toJson(requestBodyData);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://melhorenvio.com.br/api/v2/me/shipment/calculate"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZmY5NmQ5NmUzZmY0YjQ2YmYyM2YwNDYzMTk3ODAyOTQyZTVhNzlkOTg0MzRmYWJlZDBmNTc3NDU3NDg4NjRkM2NkOGU5MWY4NjQwYTZlMTQiLCJpYXQiOjE3MTIxMDM1MTguMzE1ODg5LCJuYmYiOjE3MTIxMDM1MTguMzE1ODkxLCJleHAiOjE3NDM2Mzk1MTguMzA2NDY1LCJzdWIiOiI5YmI3MDMwOC1kYzg3LTQ2YWItYjhlZS1kMTExMjcxYjA0NjQiLCJzY29wZXMiOlsic2hpcHBpbmctY2FsY3VsYXRlIl19.CvbX3KTBlatMawbjJhmenmtWZD6otSzvXzaiFWo2yEVteiO9W248DGzlWM9WvZP3NM09-91XDf4SK8W0gWwQ7tt0zT7ZPMf4Q8xsJih3DussjcNsD-r2KSnESvFcfGq-WfLiZMisqK1ll1qdOJyEbpIuGQ74r4njgzKdaRd0fANtuqP4qFs2P4XBVoGdCVyvwZp50SDx_OFqsyMZvNdp1qrAhnWQamIJ6KSgRPELDiqlLOaNmDTjXv7q38GpzzsG795JiWKpL966C-PC5SlgoAzH4kCHNUVMCzAlKKWL3GjIemqVoqi6qCLZ2s_igjEP8QPhcIsIWHE3nQKhX7lQejW9cxn5TLuIna-KOba-rJIJAVBaecSq-b5rkkUxzQ4hmCABKAXCtBg4x6oVaDLZ2K0dMMd0o0xEQxhIpuISCpskLtdzWDKXemCl6_vTcD4dpUptlXgHf0YRCP4OynJxwv-cgjhi_kGfNCsGs9rOrc9LUdTH1H6fM17GKdfB6kFMRxYn_cbMsX3LDN-A4n2RO8YMFMpK1Y9S7Ntw-kpoyHRfF2ZwOg8LETX-Rax7Mcm7a4dGeA1yqYqF9eScR9fc_KVcmzaTE_qxMu9l8LuPWMkFVTz3PjQd0OE6LxeHzQHbze2VmT7Cauec6clsxB3ar0GTDLWGEBQchMFkSQlROko")
                .header("User-Agent", "Aplicação matheushajer15@gmail.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(json))
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

    }


}

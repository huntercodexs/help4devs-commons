//package com.huntercodexs.demo.samples.retry.mongo;
//
//import org.springframework.retry.support.RetryTemplate;
//import org.springframework.retry.backoff.ExponentialBackOffPolicy;
//import org.springframework.retry.policy.SimpleRetryPolicy;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PessoaRetryTemplateService {
//
//    private final PessoaRepository repository;
//    private final RetryTemplate retryTemplate;
//
//    public PessoaRetryTemplateService(PessoaRepository repository) {
//        this.repository = repository;
//        this.retryTemplate = createTemplate();
//    }
//
//    private RetryTemplate createTemplate() {
//        RetryTemplate template = new RetryTemplate();
//
//        SimpleRetryPolicy policy = new SimpleRetryPolicy();
//        policy.setMaxAttempts(3); // 👈 número de tentativas
//
//        ExponentialBackOffPolicy backOff = new ExponentialBackOffPolicy();
//        backOff.setInitialInterval(2000);
//        backOff.setMultiplier(2.0);
//        backOff.setMaxInterval(10000);
//
//        template.setRetryPolicy(policy);
//        template.setBackOffPolicy(backOff);
//
//        return template;
//    }
//
//    public Pessoa salvar(Pessoa pessoa) {
//        return retryTemplate.execute(context -> {
//            System.out.println("Tentativa #" + context.getRetryCount());
//            return repository.save(pessoa);
//        });
//    }
//}
//

## Fila Simples (FIFO)
  ### ✔️ Queue com LinkedList
```` java
Queue<Task> taskQueue = new LinkedList<>();
````
- **Uso**: Fila básica, não concorrente.
- **API**: Java desde versões antigas.
- **Thread-safe**: ❌ Não.

## Filas Concorrentes (Non-blocking / Lock-free)
 ### ✔️ ConcurrentLinkedQueue
``` java
Queue<Task> taskQueue = new ConcurrentLinkedQueue<>();
```
- **Uso**: Concorrência leve (várias threads adicionando/removendo).
- **API**: Desde Java 6.
- **Thread-safe**: ✅ Sim, com acesso lock-free.
- **Ordem**: FIFO.

## Filas Concorrentes Bloqueantes
 ### ✔️ LinkedBlockingQueue
``` java
BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
```
- **Uso**: Produtor/consumidor com bloqueio automático.
- **Capacidade**: Pode ser limitada ou ilimitada.
- **Thread-safe**: ✅ Sim.
- **Moderna**: Muito usada com Executors.

### ✔️ ArrayBlockingQueue
```java
BlockingQueue<Task> taskQueue = new ArrayBlockingQueue<>(100);
```
- **Uso**: Fila com tamanho fixo (mais previsível em consumo de memória).
- **Thread-safe**: ✅ Sim.
- **Desempenho**: Leve para filas pequenas ou previsíveis.

## ✔️ SynchronousQueue
```java
BlockingQueue<Task> taskQueue = new SynchronousQueue<>();
```
- **Uso**: Transferência direta entre threads (sem buffer).
- **Thread-safe**: ✅ Sim.
- **Quando usar**: Para execução imediata entre produtor e consumidor.

## Filas com Priorização
 ### ✔️ PriorityQueue
```java
Queue<Task> taskQueue = new PriorityQueue<>();
```
- **Uso**: Tarefas com diferentes prioridades.
- **Requer**: Comparable<Task> ou Comparator<Task>.
- **Thread-safe**: ❌ Não.

### ✔️ PriorityBlockingQueue
```java
BlockingQueue<Task> taskQueue = new PriorityBlockingQueue<>();
```
- **Uso**: Igual à PriorityQueue, mas thread-safe.
- **API**: Desde Java 5.
- **Aplicação**: Threads consumidoras pegam tarefas com maior prioridade.

## Filas com Atraso (Agendamento)
 ### ✔️ DelayQueue
```java
DelayQueue<DelayedTask> taskQueue = new DelayQueue<>();
```
- **Uso**: Tarefas que devem ser executadas após um tempo.
- **Requer**: Tarefa implementando Delayed.
- **Thread-safe**: ✅ Sim.

## Fila Reativa / Virtual Threads (Java 21+)
 ### ✔️ Usando Executors.newVirtualThreadPerTaskExecutor() (Java 21+)
```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
executor.submit(() -> {
// Executa tarefa em uma virtual thread
});
```
- **Uso**: Processamento massivo de tarefas sem sobrecarregar threads nativas.
- **API**: Desde Java 21 (preview no 19/20).
- **Moderno**: ✅ Ideal para apps altamente escaláveis.

## Fila Customizada (Java 21+) com StructuredTaskScope
 ### ✔️Java 21 trouxe melhorias com StructuredTaskScope para controle de execução concorrente com estrutura.
``` java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
scope.fork(() -> process(task));
scope.join().throwIfFailed();
}
```
- **Uso**: Estrutura moderna para tarefas paralelas.
- **Thread-safe**: Gerenciado automaticamente.
- **Ideal para**: Pipeline de tarefas bem organizadas.

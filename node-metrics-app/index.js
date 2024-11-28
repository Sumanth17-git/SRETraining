const express = require('express');
const client = require('prom-client');

const app = express();
const port = 9991;

// Enable collection of default metrics (like process and memory metrics)
client.collectDefaultMetrics();

// Define custom metrics
const requestCounter = new client.Counter({
  name: 'node_request_count_total',
  help: 'Total number of requests',
  labelNames: ['status'],
});

const requestDurationHistogram = new client.Histogram({
  name: 'node_request_duration_seconds',
  help: 'Duration of HTTP requests in seconds',
  labelNames: ['method', 'route', 'status'],
  buckets: [0.1, 0.5, 1, 2, 5] // Define bucket boundaries in seconds
});

// Middleware to track request duration
app.use((req, res, next) => {
  const end = requestDurationHistogram.startTimer();
  res.on('finish', () => {
    end({ method: req.method, route: req.path, status: res.statusCode });
    requestCounter.inc({ status: res.statusCode });
  });
  next();
});

// Basic Routes
app.get('/', (req, res) => {
  res.send('Hello from Node Metrics App');
});

app.get('/error', (req, res) => {
  res.status(500).send('Internal Server Error');
});

// Endpoint to expose metrics for Prometheus
app.get('/metrics', async (req, res) => {
  res.set('Content-Type', client.register.contentType);
  res.end(await client.register.metrics());
});

// Start the server
app.listen(port, () => {
  console.log(`App running at http://localhost:${port}`);
});


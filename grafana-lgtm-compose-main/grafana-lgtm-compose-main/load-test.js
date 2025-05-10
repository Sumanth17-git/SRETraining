import http from 'k6/http';
import { sleep } from 'k6';

export default function () {
  const headers = { 'accept': 'application/json' };

  // 1. GET: /coinable_quote?symbol=eth
  http.get('http://localhost:8080/coinable_quote?symbol=eth', { headers });

  // 2. GET: /coinable_quote/db?symbol=btc
  http.get('http://localhost:8080/coinable_quote/db?symbol=btc', { headers });

  // 3. GET: /metrics
  //http.get('http://localhost:8080/metrics', { headers });

  // 4. POST: /coinable_quote/save?symbol=btc
  http.post('http://localhost:8080/coinable_quote/save?symbol=btc', '', { headers });

  sleep(1);  // Pause for 1 second between iterations
}

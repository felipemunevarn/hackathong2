const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'https://hackaton-3cnf.onrender.com',
      changeOrigin: true,
      pathRewrite: {
        '^/api': ''
      }
    })
  );
};

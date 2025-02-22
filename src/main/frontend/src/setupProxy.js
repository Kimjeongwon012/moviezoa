const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api',
        createProxyMiddleware({
            target: 'http://localhost:8080',  // 백엔드 포트 설정
            changeOrigin: true,
            logLevel: 'debug',  // 디버깅 활성화
        })
    );
};

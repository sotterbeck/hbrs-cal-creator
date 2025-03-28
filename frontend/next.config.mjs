/** @type {import('next').NextConfig} */
const nextConfig = {
  output: 'standalone',
  env: {
    PRIVACY_POLICY_URL: process.env.PRIVACY_POLICY_URL,
  },
};

export default nextConfig;

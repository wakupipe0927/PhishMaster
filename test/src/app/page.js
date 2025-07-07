"use client"
import Image from "next/image";
import { useState } from "react";

export default function Home() {
  const [registerEmail, setRegisterEmail] = useState("");
  const [registerPassword, setRegisterPassword] = useState("");
  const [registerUsername, setRegisterUsername] = useState("");
  const [registerMessage, setRegisterMessage] = useState("");

  const [loginEmail, setLoginEmail] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [loginMessage, setLoginMessage] = useState("");

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:8080/register?email=${encodeURIComponent(registerEmail)}&password=${encodeURIComponent(registerPassword)}&username=${encodeURIComponent(registerUsername)}`, {
        method: "GET",
      });
      const result = await response.text();
      setRegisterMessage(result);
    } catch (error) {
      console.error("Error during registration:", error);
      setRegisterMessage("注册失败, 未知错误");
    }
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:8080/login?email=${encodeURIComponent(loginEmail)}&password=${encodeURIComponent(loginPassword)}`, {
        method: "GET",
      });
      const result = await response.text();
      setLoginMessage(result);
    } catch (error) {
      console.error("Error during login:", error);
      setLoginMessage("登陆失败");
    }
  };

  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <main className="flex flex-col gap-[32px] row-start-2 items-center sm:items-start w-full max-w-md">
        {/* Registration Form */}
        <div className="w-full">
          <h2 className="text-lg font-semibold mb-2">注册</h2>
          <form onSubmit={handleRegister} className="flex flex-col gap-2">
            <input
              type="text"
              placeholder="用户名"
              value={registerUsername}
              onChange={(e) => setRegisterUsername(e.target.value)}
              required
              className="p-2 border border-gray-300 rounded"
            />
            <input
              type="email"
              placeholder="邮箱"
              value={registerEmail}
              onChange={(e) => setRegisterEmail(e.target.value)}
              required
              className="p-2 border border-gray-300 rounded"
            />
            <input
              type="password"
              placeholder="密码"
              value={registerPassword}
              onChange={(e) => setRegisterPassword(e.target.value)}
              required
              className="p-2 border border-gray-300 rounded"
            />
            <button
              type="submit"
              className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 transition-colors"
            >
              注册
            </button>
            {registerMessage && <p className="mt-2 text-sm text-red-600">{registerMessage}</p>}
          </form>
        </div>

        {/* Login Form */}
        <div className="w-full">
          <h2 className="text-lg font-semibold mb-2">登录</h2>
          <form onSubmit={handleLogin} className="flex flex-col gap-2">
            <input
              type="email"
              placeholder="邮箱"
              value={loginEmail}
              onChange={(e) => setLoginEmail(e.target.value)}
              required
              className="p-2 border border-gray-300 rounded"
            />
            <input
              type="password"
              placeholder="密码"
              value={loginPassword}
              onChange={(e) => setLoginPassword(e.target.value)}
              required
              className="p-2 border border-gray-300 rounded"
            />
            <button
              type="submit"
              className="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600 transition-colors"
            >
              登录
            </button>
            {loginMessage && <p className="mt-2 text-sm text-red-600">{loginMessage}</p>}
          </form>
        </div>

        <div className="flex gap-4 items-center flex-col sm:flex-row">
          <a
            className="rounded-full border border-solid border-transparent transition-colors flex items-center justify-center bg-foreground text-background gap-2 hover:bg-[#383838] dark:hover:bg-[#ccc] font-medium text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5 sm:w-auto"
            href="https://vercel.com/new?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
            target="_blank"
            rel="noopener noreferrer"
          >
            <Image
              className="dark:invert"
              src="/vercel.svg"
              alt="Vercel logomark"
              width={20}
              height={20}
            />
            Deploy now
          </a>
          <a
            className="rounded-full border border-solid border-black/[.08] dark:border-white/[.145] transition-colors flex items-center justify-center hover:bg-[#f2f2f2] dark:hover:bg-[#1a1a1a] hover:border-transparent font-medium text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5 w-full sm:w-auto md:w-[158px]"
            href="https://nextjs.org/docs?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
            target="_blank"
            rel="noopener noreferrer"
          >
            Read our docs
          </a>
        </div>
      </main>
      <footer className="row-start-3 flex gap-[24px] flex-wrap items-center justify-center">
        <a
          className="flex items-center gap-2 hover:underline hover:underline-offset-4"
          href="https://nextjs.org/learn?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          <Image
            aria-hidden
            src="/file.svg"
            alt="File icon"
            width={16}
            height={16}
          />
          Learn
        </a>
        <a
          className="flex items-center gap-2 hover:underline hover:underline-offset-4"
          href="https://vercel.com/templates?framework=next.js&utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          <Image
            aria-hidden
            src="/window.svg"
            alt="Window icon"
            width={16}
            height={16}
          />
          Examples
        </a>
        <a
          className="flex items-center gap-2 hover:underline hover:underline-offset-4"
          href="https://nextjs.org?utm_source=create-next-app&utm_medium=appdir-template-tw&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          <Image
            aria-hidden
            src="/globe.svg"
            alt="Globe icon"
            width={16}
            height={16}
          />
          Go to nextjs.org →
        </a>
      </footer>
    </div>
  );
}
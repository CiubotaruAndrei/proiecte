----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/16/2018 02:45:24 PM
-- Design Name: 
-- Module Name: test - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity test is
    Port ( clk : in STD_LOGIC;
           btn : in STD_LOGIC_VECTOR(4 downto 0);
           sw : in STD_LOGIC_VECTOR(15 downto 0);
           led : out STD_LOGIC_VECTOR(15 downto 0);
           an : out STD_LOGIC_VECTOR (3 downto 0);
           cat : out STD_LOGIC_VECTOR (6 downto 0));
end test;

architecture Behavioral of test is
signal enable:std_logic;
signal digits:std_logic_vector(15 downto 0);
signal reset:std_logic;
signal PCout:std_logic_vector(15 downto 0);
signal PCSrc:std_logic;

signal Instr:std_logic_vector(15 downto 0);
signal RegDst:std_logic;
signal ExtOp:std_logic;
signal Branch:std_logic;
signal Jump:std_logic;
signal MemWrite:std_logic;
signal MemtoReg:std_logic;
signal RegWrite:std_logic;
signal ALUSrc:std_logic;
signal ALUOp:std_logic_vector(2 downto 0);
signal wd:std_logic_vector(15 downto 0);
signal rd1,rd2,ext_imm:std_logic_vector(15 downto 0);
signal sa:std_logic;
signal func:std_logic_vector(2 downto 0);
signal zero:std_logic;
signal ALURes:std_logic_vector(15 downto 0);
signal BranchAdrr:std_logic_vector(15 downto 0);
signal JumpAdrr:std_logic_vector(15 downto 0);
signal MemData:std_logic_vector(15 downto 0);
signal ALUOut:std_logic_vector(15 downto 0);
begin

    C1: entity work.MPG(Behavioral) port map(clk,btn(0),enable);
    C2: entity work.MPG(Behavioral) port map(clk,btn(1),reset);
    C3: entity work.IF1 port map(clk,enable,reset,JumpAdrr,BranchAdrr,Jump,PCSrc,Instr,PCout);
    C4: entity work.SSD(Behavioral) port map(clk,digits,an,cat);
    C5: entity work.ID port map(clk,enable,Instr,wd,RegWrite,RegDst,ExtOp,rd1,rd2,ext_imm,func,sa);
    C6: entity work.EX port map(PCout,rd1,rd2,ext_imm,ALUSrc,sa,func,AlUop,zero,ALURes,BranchAdrr);
    C7: entity work.MEM port map(clk,enable,MemWrite,ALURes,rd2,MemData,ALUOut);
    --UC
    
    process(Instr)
    begin
        RegDst<='0';
        ExtOp<='0';
        ALUSrc<='0';
        Branch<='0';
        Jump<='0';
        MemWrite<='0';
        MemtoReg<='0';
        RegWrite<='0';
        ALUOp<="000";
        case Instr(15 downto 13) is
        when "000" =>   --Tip R
            RegDst<='1'; 
            RegWrite<='1';
        when "010" =>   --lw
            ExtOp<='1'; 
            ALUSrc<='1';
            MemtoReg<='1';
            RegWrite<='1';
            ALUOp<="001";
         when "011" =>  --sw
            ExtOp<='1'; 
            ALUSrc<='1';
            MemWrite<='1';
            ALUOp<="001";
         when "001" =>  --addi
            ExtOP<='1';
            ALUSrc<='1';
            RegWrite<='1';
            AlUOp<="001";
         when "100" =>  --beq
            ExtOP<='1';
            Branch<='1';
            AlUOp<="010";
         when "101" =>  --andi
            ALUSrc<='1';
            RegWrite<='1';
            ALUOp<="011";
         when "110" =>  --slti
            ALUSrc<='1';
            RegWrite<='1';
            ALUOp<="100";
         when "111" =>  --j
            Jump<='1';
            ALUOp<="001";
        end case;
    end process;
    
    --WB
    process(MemtoReg)
    begin
        if MemtoReg ='1' then
            wd<=MemData;
        else 
            wd<=ALUOut;
        end if;
    end process;
    
    --calc JumpAdrr
    
    JumpAdrr <= "000" & Instr(12 downto 0);
    
    --calcul PCSrc
    
    PCSrc <= Branch and zero;
    
    process(sw(7 downto 5))
    begin
        case sw(7 downto 5) is
        when "000" => digits<=Instr;
        when "001" => digits<=PCout;
        when "010" => digits<=rd1;
        when "011" => digits<=rd2;
        when "100" => digits<=wd;
        when "101" => digits<=ALURes;
        when "110" => digits<=MemData;
        when "111" => digits<=wd;
     end case;
     end process;
    
    led(15)<=RegDst;
    led(14)<=ExtOp;
    led(13)<=ALUsrc;
    led(12)<=Branch;
    led(11)<=Jump;
    led(10)<=MemWrite;
    led(9)<=MemtoReg;
    led(8)<=RegWrite;
    
    led(7 downto 5) <= func;
    
    led(2)<=ALUOp(2);
    led(1)<=ALUOp(1);
    led(0)<=ALUOp(0);
     
end Behavioral;

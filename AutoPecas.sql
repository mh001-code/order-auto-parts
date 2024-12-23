PGDMP      	                |            pedidoAutoPecas    16.2    16.2 ,    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24590    pedidoAutoPecas    DATABASE     �   CREATE DATABASE "pedidoAutoPecas" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
 !   DROP DATABASE "pedidoAutoPecas";
                postgres    false            �            1259    32884    cliente    TABLE     �   CREATE TABLE public.cliente (
    codigo integer NOT NULL,
    nome character varying NOT NULL,
    endereco character varying,
    telefone bigint
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    32918    cliente_codigo_seq    SEQUENCE     {   CREATE SEQUENCE public.cliente_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.cliente_codigo_seq;
       public          postgres    false    217            �           0    0    cliente_codigo_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.cliente_codigo_seq OWNED BY public.cliente.codigo;
          public          postgres    false    222            �            1259    24604    id_pedido_seq    SEQUENCE     v   CREATE SEQUENCE public.id_pedido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.id_pedido_seq;
       public          postgres    false            �            1259    32898 
   itempedido    TABLE     �   CREATE TABLE public.itempedido (
    codigo integer NOT NULL,
    qtdeitem integer NOT NULL,
    valorvenda integer NOT NULL,
    codigopedido integer NOT NULL,
    codigoproduto integer NOT NULL
);
    DROP TABLE public.itempedido;
       public         heap    postgres    false            �            1259    32922    itempedido_codigo_seq    SEQUENCE     ~   CREATE SEQUENCE public.itempedido_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.itempedido_codigo_seq;
       public          postgres    false    219            �           0    0    itempedido_codigo_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.itempedido_codigo_seq OWNED BY public.itempedido.codigo;
          public          postgres    false    223            �            1259    32937    metodopg_codigo_seq    SEQUENCE     |   CREATE SEQUENCE public.metodopg_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.metodopg_codigo_seq;
       public          postgres    false            �            1259    32930    metodopg    TABLE     �   CREATE TABLE public.metodopg (
    codigo integer DEFAULT nextval('public.metodopg_codigo_seq'::regclass) NOT NULL,
    descricao character varying NOT NULL
);
    DROP TABLE public.metodopg;
       public         heap    postgres    false    226            �            1259    32903 	   pagamento    TABLE     �   CREATE TABLE public.pagamento (
    codigo integer NOT NULL,
    valortotal integer NOT NULL,
    codigopedido integer NOT NULL,
    metodopg integer NOT NULL
);
    DROP TABLE public.pagamento;
       public         heap    postgres    false            �            1259    32926    pagamento_codigo_seq    SEQUENCE     }   CREATE SEQUENCE public.pagamento_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.pagamento_codigo_seq;
       public          postgres    false    220            �           0    0    pagamento_codigo_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.pagamento_codigo_seq OWNED BY public.pagamento.codigo;
          public          postgres    false    224            �            1259    24591    pedido    TABLE     �   CREATE TABLE public.pedido (
    datapedido date NOT NULL,
    valortotal double precision NOT NULL,
    codigo integer DEFAULT nextval('public.id_pedido_seq'::regclass) NOT NULL,
    codigocliente integer,
    codigometodopg integer
);
    DROP TABLE public.pedido;
       public         heap    postgres    false    216            �            1259    32891    produto    TABLE     �   CREATE TABLE public.produto (
    codigo integer NOT NULL,
    descricao character varying NOT NULL,
    qtde integer NOT NULL,
    valor integer NOT NULL,
    disp boolean
);
    DROP TABLE public.produto;
       public         heap    postgres    false            �            1259    32912    produto_codigo_seq    SEQUENCE     {   CREATE SEQUENCE public.produto_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.produto_codigo_seq;
       public          postgres    false    218            �           0    0    produto_codigo_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.produto_codigo_seq OWNED BY public.produto.codigo;
          public          postgres    false    221            4           2604    32919    cliente codigo    DEFAULT     p   ALTER TABLE ONLY public.cliente ALTER COLUMN codigo SET DEFAULT nextval('public.cliente_codigo_seq'::regclass);
 =   ALTER TABLE public.cliente ALTER COLUMN codigo DROP DEFAULT;
       public          postgres    false    222    217            6           2604    32923    itempedido codigo    DEFAULT     v   ALTER TABLE ONLY public.itempedido ALTER COLUMN codigo SET DEFAULT nextval('public.itempedido_codigo_seq'::regclass);
 @   ALTER TABLE public.itempedido ALTER COLUMN codigo DROP DEFAULT;
       public          postgres    false    223    219            7           2604    32927    pagamento codigo    DEFAULT     t   ALTER TABLE ONLY public.pagamento ALTER COLUMN codigo SET DEFAULT nextval('public.pagamento_codigo_seq'::regclass);
 ?   ALTER TABLE public.pagamento ALTER COLUMN codigo DROP DEFAULT;
       public          postgres    false    224    220            5           2604    32913    produto codigo    DEFAULT     p   ALTER TABLE ONLY public.produto ALTER COLUMN codigo SET DEFAULT nextval('public.produto_codigo_seq'::regclass);
 =   ALTER TABLE public.produto ALTER COLUMN codigo DROP DEFAULT;
       public          postgres    false    221    218            �          0    32884    cliente 
   TABLE DATA           C   COPY public.cliente (codigo, nome, endereco, telefone) FROM stdin;
    public          postgres    false    217   �/       �          0    32898 
   itempedido 
   TABLE DATA           _   COPY public.itempedido (codigo, qtdeitem, valorvenda, codigopedido, codigoproduto) FROM stdin;
    public          postgres    false    219   �1       �          0    32930    metodopg 
   TABLE DATA           5   COPY public.metodopg (codigo, descricao) FROM stdin;
    public          postgres    false    225   �2       �          0    32903 	   pagamento 
   TABLE DATA           O   COPY public.pagamento (codigo, valortotal, codigopedido, metodopg) FROM stdin;
    public          postgres    false    220   K3       �          0    24591    pedido 
   TABLE DATA           _   COPY public.pedido (datapedido, valortotal, codigo, codigocliente, codigometodopg) FROM stdin;
    public          postgres    false    215   �3       �          0    32891    produto 
   TABLE DATA           G   COPY public.produto (codigo, descricao, qtde, valor, disp) FROM stdin;
    public          postgres    false    218   �3       �           0    0    cliente_codigo_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.cliente_codigo_seq', 17, true);
          public          postgres    false    222            �           0    0    id_pedido_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.id_pedido_seq', 74, true);
          public          postgres    false    216            �           0    0    itempedido_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.itempedido_codigo_seq', 116, true);
          public          postgres    false    223            �           0    0    metodopg_codigo_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.metodopg_codigo_seq', 17, true);
          public          postgres    false    226            �           0    0    pagamento_codigo_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.pagamento_codigo_seq', 23, true);
          public          postgres    false    224            �           0    0    produto_codigo_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.produto_codigo_seq', 85, true);
          public          postgres    false    221            <           2606    32921    cliente cliente_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (codigo);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    217            B           2606    32940    pagamento codigo_pagamento_pk 
   CONSTRAINT     _   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT codigo_pagamento_pk PRIMARY KEY (codigo);
 G   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT codigo_pagamento_pk;
       public            postgres    false    220            >           2606    32917    produto codigo_pk 
   CONSTRAINT     S   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT codigo_pk PRIMARY KEY (codigo);
 ;   ALTER TABLE ONLY public.produto DROP CONSTRAINT codigo_pk;
       public            postgres    false    218            @           2606    32925    itempedido itempedido_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.itempedido
    ADD CONSTRAINT itempedido_pkey PRIMARY KEY (codigo);
 D   ALTER TABLE ONLY public.itempedido DROP CONSTRAINT itempedido_pkey;
       public            postgres    false    219            D           2606    32936    metodopg metodopg_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.metodopg
    ADD CONSTRAINT metodopg_pkey PRIMARY KEY (codigo);
 @   ALTER TABLE ONLY public.metodopg DROP CONSTRAINT metodopg_pkey;
       public            postgres    false    225            :           2606    24610    pedido pedido_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (codigo);
 <   ALTER TABLE ONLY public.pedido DROP CONSTRAINT pedido_pkey;
       public            postgres    false    215            E           2606    32946    pagamento codigopedido_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT codigopedido_fk FOREIGN KEY (codigopedido) REFERENCES public.pedido(codigo) NOT VALID;
 C   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT codigopedido_fk;
       public          postgres    false    215    220    4666            F           2606    32941    pagamento metodopg_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT metodopg_fk FOREIGN KEY (metodopg) REFERENCES public.metodopg(codigo) NOT VALID;
 ?   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT metodopg_fk;
       public          postgres    false    4676    220    225            �   �  x���Mn�0���)��*���HBS�j��73�ZJ���#�m�b�������C+�Ԭ�H>�{���;3�6Ъn@v��`�+]"I#heo,���-v�1j���ݖI]�E����B�k�
a۩A*��.��w��zi�,/"h��� �J�ˋ@��t*=YV\$l��3n������#(�:�:�$pZF.�q��F#��럼n�q���Ԟ&6�v�M4���W^j��l5�"�nn���?��j�"�E+�)	�y��4)9۠�-�޸���EA���+�O�xozԏ�g��m�rQ�y���^�Ò-����}&�q�b��	�$<gM���>[U��<�3��,��W��j���Nv���̼$��������ʲ\B�,�7�&|��q�~uǼ`m/���k����@�U��#�o�����=��N%��\<e��O�W�Jm�7/gb-s����c��Qit�N�,�/9珸dr      �     x�M�Q�!C��0[�z���96Qzz���W!iS�5��7��6��l��i[N�0y�����jl�(����U���*���L��	��]S��b s�ɪ �T��F�;(�U���?QS�L�������	0e?�X6��E{���@Z���Lk�+&�?�_4�h�%�T;���{�ޖ	%�A"�������r��E������8���P?<���~�X��kOn1�{Nn1~��l/������y;���8vc:�K�{�|�D���_A      �   S   x�3�t���H�,��2�tN,*9�8_!%U���ʤ̒|.c� �.S�T"Xƹ(5$ch�������eh�锟�
����� ~�      �   A   x����0�0LeHm'�t�9�|N'Nh���ETD�*z�I-$-x�����u�A��{��?�t�      �   M   x�]���0��ы���H�����5�h�ƉT"&:�#�q�D~�Z�����.��W�Gy����.�m�X      �   �  x��W�v#�]�|E�r�-��Z����Q��%����!��n0��9���I>�9Y%_�� MJ>��(�.�[�
̙T��+�P��y8c�7����~�?}�TT�TU�2h�i�0�4�[U5�E]����l�?#4 뤼nd��F�+!,�0\�hE]m9CYg�c�?��d��G+_[Q�T���0e�y�@��lD���2��Љ���$�Z��F
��0Sm�! �����9i)�RY�<���@c�& 0��)�6�l�_`�"+U������X��*+%�~�؊���B�a�0�|#�~����,��:���=W�搨S�J5?KD���Μ��1�Pz.s�Q��Y��Pu���or�'�"��������m�7_����R0�;�T�/e�������H�s����?��6 4��1��y�����R��G�L!�΃Q���y�9w����������E�Ӎ���b� k��4ok}�	������0S�2
�gXaJ��U<s���-�	�|�'& j	�������^D~�<����H�3��A����FC��+^�G��4�T��1ک����w��hb0����q�ʕ:S����~	�dÜjmO���-���|�[����x#���Z�<����)��L��*pX�r�i+���?��#����l�vs�-���5�y�(<5�9Q֖��;�����#ٻX[4ҙ�ؙ���jsuɞ�ȷ	�E@`��9U�C[6�s�����X�}��Vx�^�X�Z�Ć�
$<�re��S���,<
L�?}�X�#�=��B�ʿ���Z�r�)�B�I��s)r ��$
�x�K�cgx�(��ͥ�YO	���<-Υ�;�^�b+*�h];�:"-1J�`B��U~��x����I?�{�Y�t$N�Q��4n1p�@G���q�k�t�Ϛӕa�(M��o;q㘰Bt���=x82"]jB��h(�X������sM�����{T�j��uq��r��-j;*�c �#Q�0j��ƺβR�Q�<?	nD#�Ӭ�LC�n�^��2�42����\�g�ww򓞣�qĜ��6&���+�ӑ���gұ���9����d���l/�C_7v�^bA��(�Xq�|]�F�o;��#�f:�+�4�-x䑋�`�P�j(����ڣZ�[��
PZ�е�7�z0 �t���ء�cJB�,����;Y�J��ް-�'k�Ԙue�'jl�j9�cL�,�v��w����3�8�F��p�y��M�{׈���$w����N�G��|�8^S����ab���_#R�1=Wzд�k��P֑G�4,x=�6G�O�v�92�JxX*-Qi�v�@�m��C�d����'
b�k��k�\B�3[�F�(��a��t9:L��5��*�Vj�@���=p3��)M�������Ό4x���DB�����
���v���7w��@�3S�7[++(uo��Y�[��!`o�� �c��s�|�M����Lց����8D>�-,�
��	�i����F��ξï��~��h���7�Q�FDF���k�_��o��W��G��k��x�t/�F���W�^ u�Al��<`&3�~Y"oe��/��%������,U�8�k��J�ָT)$��ũ�ĿYB1x�'��0�@����R�/B���q���at ��}�t�1����B?��'�5����7LG�7PK!;9�G�^f�r�̝�\�-���Z-�z�G��SP��d�^0��#<x/A|�Y8.��$��N_ɨ��Vt���pHx���s�3�ȭQ����a��y&̷�����=;"������D�k`p�JW9z�FS�7($['���xs/�J֖*�*Sf�����c��Ҩ�e�����z���tػ�ּ~��G3��V����3S���']9��O����r�ӻ�y#h��߾{��w4�˕     